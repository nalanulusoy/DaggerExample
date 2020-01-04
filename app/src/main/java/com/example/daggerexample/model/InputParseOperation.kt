package com.example.daggerexample.model


import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import java.util.regex.Pattern


class InputParseOperation constructor(context: Context){
   var context: Context
    init {
       this.context=context
    }
    fun getHero(input: String): Hero {
        val hero = Hero(context)
        hero.healthPower = getHealthPower("Hero", input)
        hero.attackPoints = getAttackPoints("Hero", input)
        return hero
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    fun getRoute(input: String): List<Position> {

        val routeList = ArrayList<Position>()
        val routePat = Pattern.compile(ProjectConstant.positionRegex)
        val routeMatcher = routePat.matcher(input)

        while (routeMatcher.find()) {
            val group = routeMatcher.group()

            val enemyTypePattern = Pattern.compile("[a-z,A-Z]+")
            val enemyTypeMatcher = enemyTypePattern.matcher(group)
            if (enemyTypeMatcher.find()) {
                val enemyType = enemyTypeMatcher.group()

                val positionPattern = Pattern.compile("[0-9]+")
                val positionMatcher = positionPattern.matcher(group)

                if (positionMatcher.find()) {
                    val position = Integer.valueOf(positionMatcher.group())
                    val Location = Position(getEnemy(enemyType, input), position)
                    routeList.add(Location)
                }
            }
        }


        val resource = getResource(input)
        //remove the routes that are ahead of the resource so that hero does not have to fight with those enemies.
        val iterator = routeList.iterator()
        while (iterator.hasNext()) {
            val Location = iterator.next()
            if (Location.position > resource.distance) {
                iterator.remove()
            }
        }

        //sort using position comparator
        if (!routeList.isEmpty()) {
            routeList.sortWith(LocationComparator())
        }

        return routeList
    }

    private fun getResource(input: String): Resource {
        val resourcesPat = Pattern.compile(ProjectConstant.resourcesRegex)
        val resourceMatcher = resourcesPat.matcher(input)

        while (resourceMatcher.find()) {
            val resourceMatch = resourceMatcher.group()
            val metersPattern = Pattern.compile("[0-9]+")
            val metersMatchMatcher = metersPattern.matcher(resourceMatch)
            if (metersMatchMatcher.find()) {
                val metersMatch = Integer.valueOf(metersMatchMatcher.group())
                return Resource(metersMatch)
            }
        }
        return Resource(0)
    }

    private fun getEnemy(type: String, input: String): Enemy? {

        val pattern = Pattern.compile(ProjectConstant.enemyRegex)
        val matcher = pattern.matcher(input)

        var enemy: Enemy? = null
        while (matcher.find()) {

            val match = matcher.group()
            val typePattern = Pattern.compile("$type is Enemy")
            val typeMatcher = typePattern.matcher(match)
            if (typeMatcher.find()) {
                enemy = Enemy(type)
                enemy.healthPower = getHealthPower(type, input)
                enemy.attackPoints = getAttackPoints(type, input)
            }
        }

        return enemy

    }

    private fun getAttackPoints(type: String, input: String): Int {
        val apPattern = Pattern.compile(type + " " + ProjectConstant.attackPointsRegex)
        val apMatcher = apPattern.matcher(input)
        if (apMatcher.find()) {
            val apMatch = input.substring(apMatcher.start(), apMatcher.end())
            val apNumeric = Pattern.compile("[0-9]+")
            val apNumMatcher = apNumeric.matcher(apMatch)
            if (apNumMatcher.find()) {
                return Integer.valueOf(apMatch.substring(apNumMatcher.start(), apNumMatcher.end()))
            }
        }
        return 0
    }

    private fun getHealthPower(type: String, input: String): Int {
        val hpPattern = Pattern.compile(type + " " + ProjectConstant.healthPowerRegex)
        val hpMatcher = hpPattern.matcher(input)
        if (hpMatcher.find()) {
            val hpMatch = input.substring(hpMatcher.start(), hpMatcher.end())
            val hpNumeric = Pattern.compile("[0-9]+")
            val hpnumMatcher = hpNumeric.matcher(hpMatch)
            if (hpnumMatcher.find()) {
                return Integer.valueOf(hpMatch.substring(hpnumMatcher.start(), hpnumMatcher.end()))
            }
        }
        return 0
    }

}