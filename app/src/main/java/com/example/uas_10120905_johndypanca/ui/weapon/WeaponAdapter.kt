import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.uas_10120905_johndypanca.R
import com.example.uas_10120905_johndypanca.ui.weapon.Weapon

class WeaponAdapter(private var weaponList: List<Weapon>) :
    RecyclerView.Adapter<WeaponAdapter.WeaponViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_weapon, parent, false)
        return WeaponViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WeaponViewHolder, position: Int) {
        val currentWeapon = weaponList[position]

        // Set the weapon icon using Glide
        Glide.with(holder.itemView)
            .load(currentWeapon.displayIcon)
            .into(holder.weaponIconImageView)

        // Set the weapon name and price
        holder.weaponNameTextView.text = currentWeapon.displayName
        holder.weaponPriceTextView.text = currentWeapon.shopData?.cost ?:"0"

        holder.weaponTypeTextView.text = currentWeapon.shopData?.categoryText ?: "Melee"

        // Set the weapon details
        val formattedOutput = formatWeaponDetails(currentWeapon)
        holder.weaponDetailsTextView.text = formattedOutput
    }

    override fun getItemCount() = weaponList.size

    class WeaponViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val weaponIconImageView: ImageView = itemView.findViewById(R.id.weaponic)
        val weaponNameTextView: TextView = itemView.findViewById(R.id.weaponname)
        val weaponTypeTextView: TextView = itemView.findViewById(R.id.weaponcat)
        val weaponPriceTextView: TextView = itemView.findViewById(R.id.weaponprice)
        val weaponDetailsTextView: TextView = itemView.findViewById(R.id.weaponDetailsTextView)
    }

    fun updateWeaponList(newWeaponList: List<Weapon>) {
        weaponList = newWeaponList
        notifyDataSetChanged()
    }
}

private fun formatWeaponDetails(weapon: Weapon): String {
    val formattedOutput = StringBuilder()
    formattedOutput.append("\n\n")
    formattedOutput.append("Primary Fire:\n")
    formattedOutput.append("- Full-Automatic\n")
    formattedOutput.append("- Fire Rate: ${weapon.weaponStats?.fireRate?.toString() ?: "0"} rounds per second\n\n")

    val damageRanges = weapon.weaponStats?.damageRanges
    if (damageRanges.isNullOrEmpty()) {
        formattedOutput.append("Damage:\n- Body: 75 | Head: 150\n\n")
    } else {
        formattedOutput.append("Damage:\n")
        damageRanges.forEach { range ->
            formattedOutput.append("- Body: ${range.bodyDamage.toInt()} | Head: ${range.headDamage.toInt()} | Leg: ${range.legDamage.toInt()} (${range.rangeStartMeters}-${range.rangeEndMeters}m)\n")
        }
        formattedOutput.append("\n")
    }

    formattedOutput.append("Magazine Size: ${weapon.weaponStats?.magazineSize?.toString() ?: "0"}\n")

    return formattedOutput.toString()
}
