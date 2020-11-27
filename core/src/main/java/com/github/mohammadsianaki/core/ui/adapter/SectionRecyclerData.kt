package com.github.mohammadsianaki.core.ui.adapter

interface SectionRecyclerData<T : RecyclerData> : RecyclerData {
    val items: List<T>
    val sectionTitle: String
    val orientation: SectionOrientation
}

interface GridSectionRecyclerData<T> : RecyclerData {
    val spanCount: Int
}

enum class SectionOrientation(val value: Int) {
    HORIZONTAL(100), VERTICAL(101), GRID_HORIZONTAL(102), GRID_VERTICAL(103)
}