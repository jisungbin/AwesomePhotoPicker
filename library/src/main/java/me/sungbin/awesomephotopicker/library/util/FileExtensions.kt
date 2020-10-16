package me.sungbin.awesomephotopicker.library.util


/**
 * Created by SungBin on 2020-10-16.
 */

sealed class FileExtensions {
    object VIDEO : FileExtensions()
    object PICTURE : FileExtensions()
    object PICTUREwithGIF : FileExtensions()
}