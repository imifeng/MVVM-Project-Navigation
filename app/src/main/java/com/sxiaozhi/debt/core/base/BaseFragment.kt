package com.sxiaozhi.debt.core.base

import android.content.*
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.view.*
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.sxiaozhi.debt.R
import com.sxiaozhi.debt.databinding.FragmentBaseBinding
import com.sxiaozhi.debt.databinding.ToastCommonBinding
import com.sxiaozhi.debt.utils.KeyboardController
import com.sxiaozhi.debt.utils.Logger
import com.sxiaozhi.debt.utils.SharedPrefService
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import javax.inject.Inject

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    @Inject
    lateinit var sharedPrefService: SharedPrefService

    open val softInputMode: Int
        get() = -1

    companion object {
        const val TAG = "BaseFragment"
    }

    // base binding using in base class
    private var _baseBinding: FragmentBaseBinding? = null
    private val baseBinding
        get() = _baseBinding!!

    // binding using in the sub class
    private var _binding: VB? = null
    protected val binding: VB
        get() = _binding!!

    private val dialogHandler: Handler = Handler(Looper.getMainLooper())

    private var tipToast: Toast? = null

    abstract fun getToolbar(): Toolbar?

    open val windowInsetsCompatView: View?
          get() = getToolbar()

    abstract fun bindFragment(inflater: LayoutInflater, container: ViewGroup): VB

    open fun allowHandleBackPress(): Boolean {
        return false
    }

    open fun onBackPress() {
        findNavController().popBackStack()
    }

    val uploadOutputDirectory: File by lazy {
        val mediaDir = requireActivity().externalMediaDirs.firstOrNull()?.let {
            File(it, "video")
        }
        if (mediaDir != null && mediaDir.exists())
            mediaDir else requireActivity().filesDir
    }

    val downloadOutputDirectory: File by lazy {
        val mediaDir = requireActivity().externalMediaDirs.firstOrNull()?.let {
            File(it, "download").apply { mkdirs() }
        }
        if (mediaDir != null && mediaDir.exists())
            mediaDir else requireActivity().filesDir
    }

    val keyboardController by lazy {
        KeyboardController(requireActivity())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateSoftInputMode()

        if (allowHandleBackPress()) {
            requireActivity().onBackPressedDispatcher.addCallback(
                this,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        onBackPress()
                    }
                }
            )
        }
    }

    private fun updateSoftInputMode() {
        if (softInputMode > -1) {
            requireActivity().window.setSoftInputMode(softInputMode)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _baseBinding = FragmentBaseBinding.inflate(inflater, container, false)
        _binding = bindFragment(inflater, baseBinding.fragmentContent)
        return baseBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // system bars for immersive mode
        windowInsetsCompatView?.let {
            ViewCompat.setOnApplyWindowInsetsListener(it) { view, windowInsets ->
                val insets = windowInsets.getInsets(WindowInsetsCompat.Type.statusBars())
                view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                    Logger.d(TAG, "insets.top = ${insets.top}")
                    topMargin = insets.top
                }
                WindowInsetsCompat.CONSUMED
            }
        }


        getToolbar()?.let {
            (activity as AppCompatActivity).setSupportActionBar(it)
            (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
            (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // required on fragment
        _binding = null
        _baseBinding = null
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onPause() {
        super.onPause()
        keyboardController.hide()
    }

    fun showProgressBar() {
        baseBinding.pbLoading.isVisible = true
    }

    fun hideProgressBar() {
        baseBinding.pbLoading.isVisible = false
    }

    fun showError(message: String) {
        showCommonToast(R.drawable.ic_warning, message)
    }

    fun showSuccess(message: String) {
        showCommonToast(R.drawable.ic_success, message)
    }

    fun showCommonToast(
        iconRes: Int? = null,
        message: String,
        gravity: Int = Gravity.CENTER,
        marginHorizontal: Int = resources.getDimensionPixelSize(R.dimen.dp_24),
        marginTop: Int = 0,
        marginBottom: Int = 0,
        dismissOnDestroy: Boolean = true
    ) {
        tipToast?.cancel()
        context?.applicationContext?.let {
            val binding = ToastCommonBinding.inflate(LayoutInflater.from(context), null, false)
            val textView = binding.message

            val lp = binding.constraint.layoutParams
            val flp = FrameLayout.LayoutParams(lp.width, lp.height).apply {
                marginStart = marginHorizontal
                marginEnd = marginHorizontal
                bottomMargin = marginBottom
                topMargin = marginTop
            }
            flp.gravity = gravity
            binding.constraint.layoutParams = flp

            textView.text = message
            try {
                iconRes?.let { icon ->
                    textView.setCompoundDrawablesWithIntrinsicBounds(
                        icon,
                        0,
                        0,
                        0
                    )
                }
            } catch (e: Exception) {
                Logger.e(TAG, e.toString())
            }
            if (dismissOnDestroy) {
                tipToast = Toast(it).apply {
                    setGravity(Gravity.FILL, 0, 0)
                    duration = Toast.LENGTH_SHORT
                    view = binding.root
                }
                tipToast?.show()
            } else {
                Toast(it).apply {
                    setGravity(Gravity.FILL, 0, 0)
                    duration = Toast.LENGTH_SHORT
                    view = binding.root
                }.show()
            }
        }
    }

    fun showPermissionDeniedDialog(
        message: String,
        onCancel: (dialog: DialogInterface) -> Unit,
    ) {
        AlertDialog.Builder(requireContext()).apply {
            setTitle(R.string.access_denied)
            setMessage(message)
            setPositiveButton(
                R.string.global_ok
            ) { _, _ ->
                startActivity(
                    Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                        data = Uri.fromParts("package", context.packageName, null)
                    }
                )
            }
            setNegativeButton(R.string.global_later) { dialog, _ ->
                onCancel(dialog)
            }
        }.show()
    }

    fun checkLoginAuth(callback: () -> Unit) {
        if (sharedPrefService.isLoginAuth()) {
            callback.invoke()
        } else {
            // login
            gotoLoginAuth()
        }
    }

    fun gotoLoginAuth() {
//        sp.logout()
//        val intent = Intent(this, LoginActivity::class.java)
//        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//        startActivity(intent)
//        finish()
    }
}
