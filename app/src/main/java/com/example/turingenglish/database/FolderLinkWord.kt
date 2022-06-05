package com.example.turingenglish.database

import org.litepal.crud.LitePalSupport

/**
 * Created by Ethan On 2022/5/29 9:50
 * God bless my code!
 */
data class FolderLinkWord(
     var wordId: Int ,
     var folderId:Int ) : LitePalSupport()


