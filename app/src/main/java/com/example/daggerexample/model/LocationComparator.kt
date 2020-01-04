package com.example.daggerexample.model

class LocationComparator : Comparator<Position> {
    override fun compare(o1: Position, o2: Position): Int {
        return Integer.compare(o1.position, o2.position)
    }
}