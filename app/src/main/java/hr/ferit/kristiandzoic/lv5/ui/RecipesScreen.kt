package hr.ferit.kristiandzoic.lv5.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import hr.ferit.kristiandzoic.lv5.R
import hr.ferit.kristiandzoic.lv5.Recipe
import hr.ferit.kristiandzoic.lv5.Routes
import hr.ferit.kristiandzoic.lv5.ui.theme.DarkGray
import hr.ferit.kristiandzoic.lv5.ui.theme.LightGray
import hr.ferit.kristiandzoic.lv5.ui.theme.Pink
import hr.ferit.kristiandzoic.lv5.ui.theme.Purple500
import hr.ferit.kristiandzoic.lv5.ui.theme.White

@Composable
fun RecipesScreen(
    recipes:List<Recipe>,
    navigation:NavController) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        ScreenTitle( title="What would you like to cook today?",  subtitle="Good morning, Matej")
        SearchBar(iconResource = R.drawable.ic_search, labelText = "Search..")
        CategoryButtons()
        LazyRow(
            modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)){
            items(recipes.size){
                RecipeCard(imageResource = recipes[it].image, title = recipes[it].title)
                {
                navigation.navigate(
                    Routes.getRecipeDetailsPath(it)
                )
                }
                Spacer(modifier = Modifier .width(8.dp))
            }
        }}
        IconButton(iconResource = R.drawable.ic_plus, label = "Add new recipe") {
        }
    }


@Composable
fun ScreenTitle(
    title: String,
    subtitle: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp)
    ) {
        Text(text = subtitle,
            style = TextStyle(
                fontWeight = FontWeight.Light,
                color = Purple500,
                fontStyle = FontStyle.Italic,
                fontSize = 12.sp
            )
        )
        Text(text=title,
            style = TextStyle (
                fontWeight = FontWeight.Bold,
                fontSize=26.sp ),
            modifier = Modifier
                .padding(top = 16.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    @DrawableRes iconResource: Int,
    labelText: String,
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(
        containerColor = Color.Transparent,
        placeholderColor = DarkGray,
        textColor = DarkGray,
        unfocusedLabelColor = DarkGray,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent
    )
) {
    var inputText by remember {
        mutableStateOf("")
    }
    TextField(
        value = inputText,
        onValueChange = { inputText = it },
        label = {
            Text(labelText)
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = iconResource),
                contentDescription = labelText,
                tint = DarkGray,
                modifier = Modifier
                    .width(16.dp)
                    .height(16.dp)
            )
        },
        colors = colors,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Composable
fun TabButton(
    text: String,
    isActive: Boolean,
    onClick: () -> Unit
) {
    Button(
        shape = RoundedCornerShape(24.dp),
        elevation = null,
        colors = if (isActive) ButtonDefaults.buttonColors(contentColor =
        White, containerColor = Pink) else
            ButtonDefaults.buttonColors(contentColor = DarkGray, containerColor =
            LightGray),
        modifier = Modifier.fillMaxHeight(),
        onClick = { onClick() }
    ) {
        Text(text)
    }
}

@Composable
fun CategoryButtons() {
    var clickedButton by remember {
        mutableStateOf(0)
    }
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(44.dp)

    ) {
        TabButton(text = "All", isActive = clickedButton==0) {
            clickedButton=0
        }
        TabButton(text = "Breakfast", isActive = clickedButton==1) {
            clickedButton=1
        }
        TabButton(text = "Lunch", isActive = clickedButton==2) {
            clickedButton=2
        }
    }
}

@Composable
fun IconButton(
    @DrawableRes iconResource: Int,
    label: String,
    onClick: () -> Unit
) {

    Button(
        onClick = { onClick() },
    ) {
        Row {
            Icon(
                painter = painterResource(id = iconResource),
                contentDescription = label
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = label,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light
                )
            )
        }
    }
}
@Composable
fun Chip(
    text: String,
    backgroundColor: Color,
    textColor: Color,
) {
    Box(
        modifier = Modifier
            .background(color = backgroundColor, shape = RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
            .padding(horizontal = 8.dp, vertical = 2.dp)
    ) {
        Text(
            text = text,
            style = TextStyle(
                color = textColor,
                fontSize = 12.sp
            )
        )
    }}
@Composable
fun RecipeCard(
    @DrawableRes imageResource : Int,
    title : String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(215.dp)
            .height(326.dp)
            .clip(RoundedCornerShape(12.dp))
    ){
        Image(
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = imageResource),
            contentDescription = "Strawberry cake"

        )

        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            (
                    Text(text = title,
                        color = Color.White
                    )
                    )
            Row {
                Chip(text = "30 mins", Color.White, Color.Magenta)
                Chip(text = "4 ingredients", Color.White, Color.Magenta)
            }
        }
    }}