@file:OptIn(ExperimentalMaterial3Api::class)

package com.hninor.vassprueba.screens

// for a `var` variable also add

// or just

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hninor.vassprueba.R
import com.hninor.vassprueba.api.entry.Results


@Composable
fun LaunchList(
    characters: List<Results>,
    onCharacterClick: (characterId: Int) -> Unit,
    onLoadMore: () -> Unit
) {

    LazyColumn {
        items(characters) { character ->
            LaunchItem(character = character, onClick = onCharacterClick)
        }
        item {

                LoadingItem()
                onLoadMore()
        }
    }
}

@Composable
private fun LaunchItem(character: Results, onClick: (characterId: Int) -> Unit) {
    ListItem(
        modifier = Modifier.clickable { character.id?.let { onClick(it) } },
        headlineContent = {
            // Mission name
            Text(text = "${character.name}")
        },
        supportingContent = {
            // Site
            Text(text = character.status ?: "")
        },
        leadingContent = {
            // Mission patch
            AsyncImage(
                modifier = Modifier.size(68.dp, 68.dp),
                model = character.image,
                placeholder = painterResource(R.drawable.ic_placeholder),
                error = painterResource(R.drawable.ic_placeholder),
                contentDescription = "Mission patch"
            )
        }
    )
}

@Composable
private fun LoadingItem() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        CircularProgressIndicator()
    }
}
