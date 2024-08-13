package com.example.quiz_app_bc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.quiz_app_bc.databinding.FragmentGameBinding

class GameFragment : Fragment() {
   lateinit var binding : FragmentGameBinding

    data class Question(
        val text: String,
        val answers: List<String>
    )

    // The first answer is the correct one.  We randomize the answers before showing the text.
    // All questions must have four answers.  We'd want these to contain references to string
    // resources so we could internationalize. (or better yet, not define the questions in code...)
    private val questions: MutableList<Question> = mutableListOf(
        Question(
            text = "What is Android Jetpack?",
            answers = listOf("all of these", "tools", "documentation", "libraries")
        ),
        Question(
            text = "Base class for Layout?",
            answers = listOf("ViewGroup", "ViewSet", "ViewCollection", "ViewRoot")
        ),
        Question(
            text = "Layout for complex Screens?",
            answers = listOf("ConstraintLayout", "GridLayout", "LinearLayout", "FrameLayout")
        ),
        Question(
            text = "Pushing structured data into a Layout?",
            answers = listOf("Data Binding", "Data Pushing", "Set Text", "OnClick")
        ),
        Question(
            text = "Inflate layout in fragments?",
            answers = listOf("onCreateView", "onViewCreated", "onCreateLayout", "onInflateLayout")
        ),
        Question(
            text = "Build system for Android?",
            answers = listOf("Gradle", "Graddle", "Grodle", "Groyle")
        ),
        Question(
            text = "Android vector format?",
            answers = listOf(
                "VectorDrawable",
                "AndroidVectorDrawable",
                "DrawableVector",
                "AndroidVector"
            )
        ),
        Question(
            text = "Android Navigation Component?",
            answers = listOf("NavController", "NavCentral", "NavMaster", "NavSwitcher")
        ),
        Question(
            text = "Registers app with launcher?",
            answers = listOf("intent-filter", "app-registry", "launcher-registry", "app-launcher")
        ),
        Question(
            text = "Mark a layout for Data Binding?",
            answers = listOf("<layout>", "<binding>", "<data-binding>", "<dbinding>")
        )
    )

    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>
    private var questionIndex = 0
    private val numQuestions = ((questions.size + 1) / 2).coerceAtMost(3)

    private var score = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_game,container,false)


        //randomize question
        randomizeQuestions()


        binding.game = this

        binding.submitButton.setOnClickListener {

            val cheackId = binding.questionRadioGroup.checkedRadioButtonId

            if (-1 != cheackId){
                var ansIndex = 0

                when(cheackId){

                    R.id.secondAnswerRadioButton -> ansIndex = 1
                    R.id.thirdAnswerRadioButton  -> ansIndex = 2
                    R.id.fourthAnswerRadioButton -> ansIndex = 3
                }

                if (answers[ansIndex] == currentQuestion.answers[0]){
                    questionIndex ++
                    score ++

                    // next questions set
                    if (questionIndex < numQuestions){
                        currentQuestion = questions[questionIndex]
                        setQuestion()
                        binding.invalidateAll()

                    }else {

                    findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameWonFragment(
                        numQuestions,
                        score
                    ))


                    }

                }else{

                    findNavController().navigate(R.id.action_gameFragment_to_gameOverFragment)

                }
            }

        }


        return binding.root
    }

    private fun randomizeQuestions() {

        questions.shuffle()
        questionIndex = 0

        // set the question
        setQuestion()
    }

    private fun setQuestion() {
        // set the current question
        currentQuestion = questions[questionIndex]

        // set the current question answer
        answers = currentQuestion.answers.toMutableList()
        // shuffle
      //  answers.shuffle()

        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.title_android_trivia_question,questionIndex + 1, numQuestions)

    }

}