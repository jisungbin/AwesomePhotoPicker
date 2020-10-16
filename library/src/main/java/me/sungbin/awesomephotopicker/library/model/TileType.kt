package me.sungbin.awesomephotopicker.library.model


/**
 * Created by SungBin on 2020-10-16.
 */

sealed class TileType {
    object PHOTO : TileType()
    object CAMERA : TileType()
    object GALLERY : TileType()
}