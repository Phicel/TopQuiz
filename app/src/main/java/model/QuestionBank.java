package model;

import java.util.Collections;
import java.util.List;

public class QuestionBank {
    private List<Question> mQuestionList;
    private int mNextQuestionNum;

    public QuestionBank(List<Question> questionList) {
        mQuestionList = questionList;

        Collections.shuffle(mQuestionList); // tire au hasard question
        mNextQuestionNum = 0;               // 1ere quest.
    }

    public Question getQuestion() {
        if (mNextQuestionNum == mQuestionList.size()) {
            mNextQuestionNum = 0;           // remise a ZERO compteur Quest.
        }

        return mQuestionList.get(mNextQuestionNum++);
    }
}
