@file:JvmName("ExtensionsUtils")

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.sparktech.newsapp.R
import org.ocpsoft.prettytime.PrettyTime
import java.text.SimpleDateFormat
import java.util.*


fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun ImageView.loadImg(imageUrl: String?) {
    if (TextUtils.isEmpty(imageUrl)) {
        Glide
                .with(context)
                .load(R.mipmap.ic_launcher)
                .into(this)
    } else {
        Glide
                .with(context)
                .load(imageUrl)
                .into(this)
    }
}

fun String?.prettyTime(): String? {
    // 2016-10-14T11:11:14Z
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
    sdf.setTimeZone(TimeZone.getTimeZone("GMT"))
    return PrettyTime().format(sdf.parse(this))
}
