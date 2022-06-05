//package com.example.turingenglish.util
//
//import com.example.turingenglish.config.ConfigData
//import com.example.turingenglish.database.Word
//import org.litepal.LitePal
//import java.text.ParseException
//
///**
// * Created by Ethan On 2022/5/22 15:28
// * God bless my code!
// */
///*背单词流程的控制器*/
//object WordController {
//    // 新学模式
//    const val NEW_LEARN = 1
//
//    // 及时复习模式
//    const val REVIEW_AT_TIME = 2
//
//    // 一般复习模式（浅度&深度）
//    const val REVIEW_GENERAL = 3
//
//    // 学习完毕
//    const val TODAY_MASK_DONE = 4
//
//    // 今天复习总共的单词量
//    var wordReviewNum = 0
//
//    // 当前ID
//    var currentWordId = 0
//
//    // 当前模式
//    var currentMode = 0
//
//    const val TAG = "WordController"
//
//    // 候选需要学习的单词
//    val needLearnWords: ArrayList<Int> = ArrayList()
//
//    // 候选需要复习的单词
//    val needReviewWords: ArrayList<Int> = ArrayList()
//
//    // 用于存放本轮刚学习过的单词，以便及时复习
//    val justLearnedWords: ArrayList<Int> = ArrayList()
//
//    // 生成候选需学习的单词
//    fun generateDailyLearnWords(lastStartTime: Long) {
//        // 获得准备数据
//        val userConfigs =
//            LitePal.where("userId = ?", ConfigData.getSinaNumLogged().toString() + "").find(
//                UserConfig::class.java
//            )
//
//        // 需要学习的，但是并没有在指定时间去学习
//        val wordNeedLearnList: List<Word> = LitePal.where(
//            "isNeedLearned = ? and justLearned = ? and needLearnDate <= ?",
//            "1",
//            "0",
//            TimeController.getCurrentDateStamp().toString() + ""
//        )
//            .select("wordId").find(Word::class.java)
//
//
//        // 得到每天需要的学习量
//        val needWordTotal: Int = userConfigs[0].wordNeedReciteNum
//        // 根本没有学习过的单词（这是供下面用来分配单词的库）
//        // 不需要学习的单词有可能是需要复习的单词，需要将其排除
//        val wordNoNeedLearnList =
//            LitePal.where("isNeedLearned = ? and isLearned = ?", "0", "0").select("wordId").find(
//                Word::class.java
//            )
//
//
//        // 这时候说明还是同一天，直接分配未学习的单词就可以了
//        var i = 0
//        for (word in wordNeedLearnList) {
//            ++i
//            if (i <= needWordTotal) needLearnWords.add(word.wordId) else break
//        }
//    }
//
//    fun generateDailyReviewWords() {
//        // 防止重复添加
//        needReviewWords.clear()
//        justLearnedWords.clear()
//
//        // 获得准备数据
//        // 已经初学过，但是并未及时复习的单词
//        val notReviewAtTimeList =
//            LitePal.where("justLearned = ? and isLearned = ?", "1", "0").select("wordId").find(
//                Word::class.java
//            )
//
//        // 浅度复习候选条件：已经学习过的并且单词掌握程度未到10的单词
//        val littleReviewList =
//            LitePal.where("isLearned = ? and masterDegree < ?", "1", "10").select("wordId")
//                .find(
//                    Word::class.java
//                )
//
//        // 深度复习候选条件：单词掌握程度达到10并且达到了单词复习的时间
//        val deepReviewList = LitePal.where("masterDegree = ?", "10").select("wordId").find(
//            Word::class.java
//        )
//
//        // (1).先找哪些单词未及时深度复习或者已经到了深度学习的阶段，找到的同时加入到候选复习单词列表
//        for (word: Word? in deepReviewList) {
//            if (word != null) {
//                when (word.deepMasterTimes) {
//                    0 -> try {
//                        // 说明未及时深度复习
//                        if (TimeController.daysInternal(
//                                word.lastMasterTime,
//                                TimeController.getCurrentDateStamp()
//                            ) > 4
//                        ) {
//                            val newWord :Word = Word()
//                            newWord.masterDegree = 8
//                            newWord.updateAll("wordId = ?", word.getWordId().toString() + "")
//                            // 加入复习列表
//                            needReviewWords.add(word.wordId)
//                        } else if (TimeController.daysInternal(
//                                word.getLastMasterTime(),
//                                TimeController.getCurrentDateStamp()
//                            ) === 4
//                        ) {
//                            // 说明已经到了深度复习的那一天
//                            // 加入复习列表
//                            needReviewWords.add(word.getWordId())
//                        }
//                    } catch (e: ParseException) {
//                        e.printStackTrace()
//                    }
//                    1 -> try {
//                        // 说明未及时深度复习
//                        if (TimeController.daysInternal(
//                                word.getLastMasterTime(),
//                                TimeController.getCurrentDateStamp()
//                            ) > 3
//                        ) {
//                            val newWord = Word()
//                            newWord.masterDegree = 8
//                            newWord.updateAll("wordId = ?", word.getWordId().toString() + "")
//                            // 加入复习列表
//                            needReviewWords.add(word.wordId)
//                        } else if (TimeController.daysInternal(
//                                word.getLastMasterTime(),
//                                TimeController.getCurrentDateStamp()
//                            ) === 3
//                        ) {
//                            // 说明已经到了深度复习的那一天
//                            // 加入复习列表
//                            needReviewWords.add(word.wordId)
//                        }
//                    } catch (e: ParseException) {
//                        e.printStackTrace()
//                    }
//                    2 -> try {
//                        // 说明未及时深度复习
//                        if (TimeController.daysInternal(
//                                word.getLastMasterTime(),
//                                TimeController.getCurrentDateStamp()
//                            ) > 8
//                        ) {
//                            val newWord = Word()
//                            newWord.masterDegree = 8
//                            newWord.updateAll("wordId = ?", word.getWordId().toString() + "")
//                            // 加入复习列表
//                            needReviewWords.add(word.getWordId())
//                        } else if (TimeController.daysInternal(
//                                word.lastMasterTime,
//                                TimeController.getCurrentDateStamp()
//                            ) === 8
//                        ) {
//                            // 说明已经到了深度复习的那一天
//                            // 加入复习列表
//                            needReviewWords.add(word.getWordId())
//                        }
//                    } catch (e: ParseException) {
//                        e.printStackTrace()
//                    }
//                }
//            }
//        }
//        // (2).把需浅度复习的单词也一并加入到候选复习单词列表
//        for (word: Word? in littleReviewList) {
//            if (word != null) {
//                needReviewWords.add(word.getWordId())
//            }
//        }
//        // (3).把需及时复习的单词也一并加入到候选复习单词列表
//        for (word: Word? in notReviewAtTimeList) {
//            if (word != null) {
//                needReviewWords.add(word.getWordId())
//            }
//        }
//
//    }
//}