package hr.ferit.kristiandzoic.lv5

import androidx.annotation.DrawableRes

data class Ingredient(
    @DrawableRes val image : Int,
    val title : String,

    val subtitle : String
)
