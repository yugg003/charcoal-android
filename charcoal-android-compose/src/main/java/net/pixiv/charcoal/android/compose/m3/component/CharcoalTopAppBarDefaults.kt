package net.pixiv.charcoal.android.compose.m3.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import net.pixiv.charcoal.android.R
import net.pixiv.charcoal.android.compose.m3.preview.CharcoalPreviewColumn
import net.pixiv.charcoal.android.compose.m3.theme.CharcoalM3Theme
import net.pixiv.charcoal.android.compose.theme.CharcoalTheme

/**
 * Charcoal デザイントークンを M3 TopAppBar の既定色として提供する。
 * 生の [androidx.compose.material3.TopAppBar] に渡して使う。
 *
 * container / scrolledContainer / title 色は
 * [CharcoalM3Theme][net.pixiv.charcoal.android.compose.m3.theme.CharcoalM3Theme] の ColorScheme
 * （surface / surfaceContainer / onSurface）と一致するため M3 の既定に委ね、
 * navigationIcon / actionIcon だけ M3 既定（onSurface / onSurfaceVariant = text1 / text2）と異なる text3 に固定する。
 * title の文字スタイルは Typography の titleLarge（regular20）で当たる。
 */
object CharcoalTopAppBarDefaults {
    /**
     * navigationIcon / actionIcon を text3 にした [TopAppBarColors] を返す。各色は引数で差し替えられる。
     */
    @Composable
    fun colors(
        navigationIconContentColor: Color = CharcoalTheme.colorToken.text3,
        actionIconContentColor: Color = CharcoalTheme.colorToken.text3,
    ): TopAppBarColors = TopAppBarDefaults.topAppBarColors(
        navigationIconContentColor = navigationIconContentColor,
        actionIconContentColor = actionIconContentColor,
    )
}

// region Preview

/**
 * CharcoalTopAppBarDefaults.colors() を TopAppBar の 4 種（Small / CenterAligned / Medium / Large）に渡した状態。
 * navigationIcon / actionIcon が text3、container / title は ColorScheme 由来。
 */
@Composable
private fun CharcoalTopAppBarGallery() {
    CharcoalPreviewColumn {
        TopAppBar(
            title = { Text("TopAppBar") },
            navigationIcon = { NavIcon() },
            actions = { ActionIcon() },
            colors = CharcoalTopAppBarDefaults.colors(),
        )
        CenterAlignedTopAppBar(
            title = { Text("CenterAligned") },
            navigationIcon = { NavIcon() },
            actions = { ActionIcon() },
            colors = CharcoalTopAppBarDefaults.colors(),
        )
        MediumTopAppBar(
            title = { Text("MediumTopAppBar") },
            navigationIcon = { NavIcon() },
            actions = { ActionIcon() },
            colors = CharcoalTopAppBarDefaults.colors(),
        )
        LargeTopAppBar(
            title = { Text("LargeTopAppBar") },
            navigationIcon = { NavIcon() },
            actions = { ActionIcon() },
            colors = CharcoalTopAppBarDefaults.colors(),
        )
    }
}

@Composable
private fun NavIcon() {
    Icon(painter = painterResource(R.drawable.ic_charcoal_close_24), contentDescription = null)
}

@Composable
private fun ActionIcon() {
    Icon(painter = painterResource(R.drawable.ic_charcoal_add_24), contentDescription = null)
}

@PreviewLightDark
@Composable
private fun CharcoalTopAppBarPreview() {
    CharcoalM3Theme { CharcoalTopAppBarGallery() }
}

// endregion
