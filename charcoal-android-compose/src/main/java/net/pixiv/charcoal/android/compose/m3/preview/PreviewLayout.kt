package net.pixiv.charcoal.android.compose.m3.preview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/** Preview 用の共通レイアウト。Surface の上に縦に並べる。 */
@Composable
internal fun CharcoalPreviewColumn(content: @Composable () -> Unit) {
    Surface {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            content()
        }
    }
}

/** Preview 用の共通レイアウト。横に並べて縦中央に揃える。 */
@Composable
internal fun CharcoalPreviewRow(content: @Composable () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        content()
    }
}
