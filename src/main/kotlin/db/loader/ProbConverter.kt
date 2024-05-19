package db.loader

import base.model.StartingHand
import java.io.BufferedReader
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*

object ProbConverter {
    /**
     * The online source is in the form of
     * xx vs. yy:
     * Alice: a/b = c
     * Bob:   d/e = f
     * Tie:   g/h = i
     */
    fun convertFromOnline(stream:InputStream): Map<Pair<StartingHand, StartingHand>, Prob> {
        val reader = BufferedReader(InputStreamReader(stream))
        val map: MutableMap<Pair<StartingHand, StartingHand>, Prob> = mutableMapOf()
        while (true) {
            val currProb = parseProb(reader)
            if (!currProb.isPresent) {
                break
            }
            val prob = currProb.get().parse()
            val probVals = prob.third;
            map[Pair(prob.first, prob.second)] = probVals
            if (prob.first != prob.second) {
                map[Pair(prob.second, prob.first)] = Prob(probVals.lose, probVals.tie, probVals.win)
            }
        }
        return map
    }

    data class OnlineProb(val line1:String, val line2: String, val line3: String, val line4: String) {
        fun parse(): Triple<StartingHand, StartingHand, Prob> {
            val handsString = line1.split(" ");
            val hand1 = StartingHand.create(handsString[0]!!)
            val hand2String = handsString[2]!!
            val hand2 = StartingHand.create(hand2String.substring(0, hand2String.length - 1))
            val winString = line2.split(" ")
            val winProb = winString[winString.size - 1].toDouble()
            val loseString = line4.split(" ")
            val loseProb = loseString[loseString.size - 1].toDouble()
            val tieString = line3.split(" ")
            val tieProb = tieString[tieString.size - 1].toDouble()
            return Triple(hand1, hand2, Prob(winProb, loseProb, tieProb))
        }
    }

    fun parseProb(reader: BufferedReader): Optional<OnlineProb> {
        val lines = mutableListOf<String>()
        for (i in 1..4) {
            lines.add(reader.readLine() ?: return Optional.empty())
        }
        return Optional.of(OnlineProb(lines[0], lines[1], lines[2], lines[3]))
    }

    fun serialize(probMap: Map<Pair<StartingHand, StartingHand>, Prob>, outputFile: File) {
        val allHands = StartingHand.allHands()
        val writer = outputFile.printWriter()
        val allHandsString = allHands.joinToString(",") {
            it.toString()
        }
        writer.println(",$allHandsString")
        allHands.forEach { hand1 ->
            val probs = allHands.joinToString(",") { hand2 ->
                probMap[Pair(hand1, hand2)].toString()
            }
            writer.println("${hand1},$probs")
        }
        writer.flush()
        writer.close()
    }
}