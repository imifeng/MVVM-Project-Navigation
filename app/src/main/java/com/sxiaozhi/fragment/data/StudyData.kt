package com.sxiaozhi.fragment.data


data class StudyBean(
    val types: List<String>?,
    val list: List<StudyInfo>?,
    val agent: AgentBean?
)

data class StudyInfo(
    val id: String,
    val question: String?,
    val answer: String?,
    val type: String?,
)