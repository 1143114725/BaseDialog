package com.example.masterkit.page.bill.record

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.LeadingIconTab
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.masterkit.R
import com.example.masterkit.composeutil.dp16
import com.example.masterkit.composeutil.dp24
import com.example.masterkit.composeutil.dp50
import com.example.masterkit.composeutil.dp8
import com.example.masterkit.composeutil.theme.Theme.LocalCustomColors

class RecordPage {

    @Preview(showBackground = true)
    @Composable
    fun RecordView() {
        TabsView()
    }

    @Composable
    fun TabsView() {
        val selected = false
        val onClick: () -> Unit = {}

//        selected: Boolean,
//        onClick: () -> Unit,
//        text: @Composable () -> Unit,
//        icon: @Composable () -> Unit,
//        modifier: Modifier = Modifier,
//        enabled: Boolean = true,
//        selectedContentColor: Color = LocalContentColor.current,
//        unselectedContentColor: Color = selectedContentColor,
//        interactionSource: MutableInteractionSource? = null

        LeadingIconTab(selected, onClick,text ={
            Text(text = "Record", Modifier.padding(dp8))
        },icon = {
            Icon(
                painter = painterResource(R.drawable.icon_app_top_bar_back),
                contentDescription = null,
                modifier = Modifier
                    .size(dp24)
            )
        })
    }

    @Composable
    fun TopNavigation() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(dp50)
                .background(LocalCustomColors.current.colorScheme.primary)

        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .height(dp50)
                    .background(LocalCustomColors.current.colorScheme.onPrimary),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {

                Text(text = "Record", Modifier.padding(dp8))

            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .height(dp50)
                    .background(LocalCustomColors.current.colorScheme.primaryContainer),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = "Record", Modifier.padding(dp8))
            }

        }
    }
}