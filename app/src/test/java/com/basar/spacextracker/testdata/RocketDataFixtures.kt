package com.basar.spacextracker.testdata

import com.basar.spacextracker.data.remote.response.Engines
import com.basar.spacextracker.data.remote.response.Height
import com.basar.spacextracker.data.remote.response.Mass
import com.basar.spacextracker.data.remote.response.Rocket
import com.basar.spacextracker.domain.uimodel.RocketUIItem
import com.basar.spacextracker.data.local.model.Rocket as LocalRocket

object RocketDataFixtures {

    fun getRockets(): List<Rocket> = listOf(
        Rocket(
            id = "1",
            flickrImages = listOf("https:1", "https:2"),
            name = "Falcon 1",
            country = "Republic of the Marshall Islands",
            company = "Intel",
            wikipedia = "Wiki",
            description = "Description",
            height = Height(22.25),
            mass = Mass(30146),
            engines = Engines(number = 1),
        ), Rocket(
            id = "2",
            flickrImages = listOf("https:1", "https:2"),
            name = "Falcon 9",
            country = "United States",
            company = "SpaceX",
            wikipedia = "Wiki",
            description = "Description",
            height = Height(70.0),
            mass = Mass(549054),
            engines = Engines(number = 9),
        ), Rocket(
            id = "3",
            flickrImages = listOf("https:1", "https:2"),
            name = "Falcon Heavy",
            country = "United States",
            company = "Tesla",
            wikipedia = "Wiki",
            description = "Description",
            height = Height(70.0),
            mass = Mass(70),
            engines = Engines(number = 27),
        ), Rocket(
            id = "4",
            flickrImages = listOf("https:1", "https:2"),
            name = "Starship",
            country = "United States",
            company = "Yargici",
            wikipedia = "Wiki",
            description = "Description",
            height = Height(118.0),
            mass = Mass(1335000),
            engines = Engines(number = 37)
        )
    )

    fun getLocalRockets(): List<LocalRocket> = getRocketUIs().map(RocketUIItem::toRocket)

    fun getRocketUIs(): List<RocketUIItem> = listOf(
        RocketUIItem(
            id = "1",
            imageUrl = listOf("https:1", "https:2"),
            name = "Falcon 1",
            country = "Republic of the Marshall Islands",
            company = "Intel",
            wikipedia = "Wiki",
            description = "Description",
            height = "22.25",
            weight = "30146",
            engineCount = "1",
            isFavourite = false
        ), RocketUIItem(
            id = "2",
            imageUrl = listOf("https:1", "https:2"),
            name = "Falcon 9",
            country = "United States",
            company = "SpaceX",
            wikipedia = "Wiki",
            description = "Description",
            height = "70",
            weight = "549054",
            engineCount = "9",
            isFavourite = false
        ), RocketUIItem(
            id = "3",
            imageUrl = listOf("https:1", "https:2"),
            name = "Falcon Heavy",
            country = "United States",
            company = "Tesla",
            wikipedia = "Wiki",
            description = "Description",
            height = "70",
            weight = "1420788",
            engineCount = "27",
            isFavourite = false
        ), RocketUIItem(
            id = "4",
            imageUrl = listOf("https:1", "https:2"),
            name = "Starship",
            country = "United States",
            company = "Yargici",
            wikipedia = "Wiki",
            description = "Description",
            height = "118",
            weight = "1335000",
            engineCount = "37",
            isFavourite = false
        )
    )

    fun buildFavouriteRocketUIs(vararg ids: Int): List<RocketUIItem> {
        val rocketList = getRocketUIs()
        ids.forEach {
            rocketList[it].isFavourite = true
        }
        return rocketList
    }

    fun buildFavouriteRockets(vararg ids: Int): List<LocalRocket> {
        val rocketList = getLocalRockets()
        ids.forEach {
            rocketList[it].copy(isFavorite = true)
        }
        return rocketList
    }
}