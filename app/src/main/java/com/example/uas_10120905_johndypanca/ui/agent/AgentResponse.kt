package com.example.uas_10120905_johndypanca.ui.agent

import android.os.Parcel
import android.os.Parcelable

data class AgentResponse(
    val data: List<Data>
) : Parcelable {
    data class Data(
        val uuid: String? = null,
        val displayName: String? = null,
        val fullPortrait: String? = null,
        val assetPath: String? = null,
        val isPlayableCharacter: Boolean? = null,
        val role: Role,
        val abilities: List<Ability>,
        val voiceLine: VoiceLine? // Add the voice line information
    ) : Parcelable {
        data class Role(
            val uuid: String? = null,
            val displayName: String? = null,
            val displayIcon: String? = null,
            val assetPath: String? = null
        ) : Parcelable {
            // ... Role class implementation ...

            constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString()
            )

            override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(uuid)
                parcel.writeString(displayName)
                parcel.writeString(displayIcon)
                parcel.writeString(assetPath)
            }

            override fun describeContents(): Int {
                return 0
            }

            companion object CREATOR : Parcelable.Creator<Role> {
                override fun createFromParcel(parcel: Parcel): Role {
                    return Role(parcel)
                }

                override fun newArray(size: Int): Array<Role?> {
                    return arrayOfNulls(size)
                }
            }
        }

        data class Ability(
            val slot: String? = null,
            val displayName: String? = null,
            val description: String? = null,
            val displayIcon: String? = null
        ) : Parcelable {
            // ... Ability class implementation ...

            constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString()
            )

            override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(slot)
                parcel.writeString(displayName)
                parcel.writeString(description)
                parcel.writeString(displayIcon)
            }

            override fun describeContents(): Int {
                return 0
            }

            companion object CREATOR : Parcelable.Creator<Ability> {
                override fun createFromParcel(parcel: Parcel): Ability {
                    return Ability(parcel)
                }

                override fun newArray(size: Int): Array<Ability?> {
                    return arrayOfNulls(size)
                }
            }
        }

        data class VoiceLine(
            val minDuration: Double? = null,
            val maxDuration: Double? = null,
            val mediaList: List<Media>? = null
        ) : Parcelable {
            data class Media(
                val id: Long? = null,
                val wwise: String? = null,
                val wave: String? = null
            ) : Parcelable {
                constructor(parcel: Parcel) : this(
                    parcel.readValue(Long::class.java.classLoader) as? Long,
                    parcel.readString(),
                    parcel.readString()
                )

                override fun writeToParcel(parcel: Parcel, flags: Int) {
                    parcel.writeValue(id)
                    parcel.writeString(wwise)
                    parcel.writeString(wave)
                }

                override fun describeContents(): Int {
                    return 0
                }

                companion object CREATOR : Parcelable.Creator<Media> {
                    override fun createFromParcel(parcel: Parcel): Media {
                        return Media(parcel)
                    }

                    override fun newArray(size: Int): Array<Media?> {
                        return arrayOfNulls(size)
                    }
                }
            }

            constructor(parcel: Parcel) : this(
                parcel.readValue(Double::class.java.classLoader) as? Double,
                parcel.readValue(Double::class.java.classLoader) as? Double,
                parcel.createTypedArrayList(Media.CREATOR)
            )

            override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeValue(minDuration)
                parcel.writeValue(maxDuration)
                parcel.writeTypedList(mediaList)
            }

            override fun describeContents(): Int {
                return 0
            }

            companion object CREATOR : Parcelable.Creator<VoiceLine> {
                override fun createFromParcel(parcel: Parcel): VoiceLine {
                    return VoiceLine(parcel)
                }

                override fun newArray(size: Int): Array<VoiceLine?> {
                    return arrayOfNulls(size)
                }
            }
        }

        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
            parcel.readParcelable(Role::class.java.classLoader)!!,
            parcel.createTypedArrayList(Ability.CREATOR)!!,
            parcel.readParcelable(VoiceLine::class.java.classLoader) // Read the VoiceLine object
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(uuid)
            parcel.writeString(displayName)
            parcel.writeString(fullPortrait)
            parcel.writeString(assetPath)
            parcel.writeValue(isPlayableCharacter)
            parcel.writeParcelable(role, flags)
            parcel.writeTypedList(abilities)
            parcel.writeParcelable(voiceLine, flags) // Write the VoiceLine object
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Data> {
            override fun createFromParcel(parcel: Parcel): Data {
                return Data(parcel)
            }

            override fun newArray(size: Int): Array<Data?> {
                return arrayOfNulls(size)
            }
        }
    }

    constructor(parcel: Parcel) : this(parcel.createTypedArrayList(Data.CREATOR)!!)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(data)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AgentResponse> {
        override fun createFromParcel(parcel: Parcel): AgentResponse {
            return AgentResponse(parcel)
        }

        override fun newArray(size: Int): Array<AgentResponse?> {
            return arrayOfNulls(size)
        }
    }
}
