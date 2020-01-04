package com.example.daggerexample.model

import android.content.Context


public class Hero  constructor(context: Context) : LivingProperties(), IAttack {

     var writer: MessageWriter
    var isAlive = true
     init {
         writer= MessageWriter(context =context )
     }

    private val output = StringBuilder()
    fun start(positions: List<Position>) {
        output.append("Hero started journey with $healthPower HP! \n")
        for (position in positions) {
            if (isAlive) {
                attack(position)
            }
        }
        if (healthPower > 0)
            output.append("Hero survived!")
        writer!!.writeMessage(output.toString())

    }

    override fun attack(position: Position) {
        val e = position.enemy
        while (healthPower > 0 && e!!.healthPower > 0) {
            healthPower = healthPower - e.attackPoints
            e.healthPower = e.healthPower - attackPoints
        }

        if (healthPower > 0) {
            output.append("Hero defeated " + e!!.type+ " with " + healthPower + " HP remaining \n")
        } else {
            output.append(e!!.type + " defeated Hero" + " with " + e.healthPower + " HP remaining \n")
            output.append("Hero is Dead!! Last seen at position " + position.position + "!! \n")
            isAlive = false
        }
    }
}