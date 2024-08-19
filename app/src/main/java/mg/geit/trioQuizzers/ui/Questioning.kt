package mg.geit.trioQuizzers.ui

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import mg.geit.trioQuizzers.OctaPersonaQuizScreen
import mg.geit.trioQuizzers.R
import mg.geit.trioQuizzers.ui.components.ButtonStart
import mg.geit.trioQuizzers.ui.components.Header
import mg.geit.trioQuizzers.ui.components.MyCardButton
import mg.geit.trioQuizzers.ui.components.PossibleResponse
import mg.geit.trioQuizzers.ui.theme.AppTheme
import mg.geit.trioQuizzers.ui.theme.reusableBrush

@SuppressLint("RememberReturnType", "AutoboxingStateCreation")
@Composable
fun QuizScreen(
    navController: NavController,
    name: String
) {
    val currentQuestionIndex = remember { mutableIntStateOf(0) }
    val listQuestion = ListQuestions.getQuestions()
    Box {
        Header(
            navController,
            "${OctaPersonaQuizScreen.AboutTheApp.name}/${name}"
        )
        ContentQuestion(
            currentQuestionIndex,
            listQuestion,
            submitAction = {
                navController.navigate("${OctaPersonaQuizScreen.Result.name}/${name}")
            }
        )
    }
}

@Composable
fun ContentQuestion(
    currentQuestionIndex: MutableState<Int>,
    listQuestions: List<Question>,
    submitAction: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(
                MaterialTheme.colorScheme.surfaceColorAtElevation(15.dp)
            )
    ) {
        QuestionScreen(
            question = listQuestions[currentQuestionIndex.value],
            currentQuestionIndex,
        )
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            var brushPrev = reusableBrush()
            var brushNex = reusableBrush()
            if (currentQuestionIndex.value == 0) {
                brushPrev = Brush.linearGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.secondary,
                        MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 30.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.padding(top = 30.dp))
                ButtonStart(
                    actionToDo = {
                        if (currentQuestionIndex.value > 0) {
                            currentQuestionIndex.value--
                        }
                    },
                    scale = 1.5f,
                    size = 40,
                    isForNavigationQuestion = true,
                    brushPrev,
                    false
                )
            }
            if (currentQuestionIndex.value == listQuestions.size - 1) {
                Column(
                    modifier = Modifier
                        .weight(3.5f)
                        .fillMaxHeight()
                        .padding(top = 30.dp),
                ) {
                    MyCardButton(
                        stringResource(R.string.terminer),
                        brush = reusableBrush(),
                        onSubmitAction = {
                            submitAction()
                        }
                    )
                }
                brushNex = Brush.linearGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.secondary,
                        MaterialTheme.colorScheme.onPrimary
                    )
                )
            } else {
                Column(
                    modifier = Modifier
                        .weight(3.5f)
                        .fillMaxHeight()
                        .padding(top = 30.dp),
                ) {
                }
            }
            Column(
                modifier = Modifier
                    .padding(end = 30.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.padding(top = 30.dp))
                ButtonStart(
                    actionToDo = {
                        if (currentQuestionIndex.value < listQuestions.size - 1) {
                            currentQuestionIndex.value++
                        }
                    },
                    scale = 1.5f,
                    size = 40,
                    isForNavigationQuestion = true,
                    brushNex,
                    true
                )
            }
        }
    }
}

@Composable
fun QuestionScreen(
    question: Question,
    currentQuestionIndex: MutableState<Int>,
) {
    Column(
        modifier = Modifier
            .padding(top = 60.dp, start = 25.dp, bottom = 20.dp)
    ) {
        Text(
            text = question.questionText,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .padding(end = 25.dp)
        )
        question.options.forEachIndexed { index, option ->
            PossibleResponse(
                stringNumber = "$index",
                response = option.response,
                onClick = {
                    ListQuestions.changeIsSelectedStateOfResponse(
                        currentQuestionIndex.value,
                        index
                    )
                    if (currentQuestionIndex.value < ListQuestions.getQuestions().size - 1) {
                        currentQuestionIndex.value++
                    }
                },
                isSelected = question.options[index].isSelected
            )
            Spacer(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp))
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewLight"
)
@Preview(showBackground = true)
@Composable
fun ShowResponse() {
    AppTheme {
        Surface(tonalElevation = 10.dp) {
            QuizScreen(
                navController = rememberNavController(),
                "JASON"
            )
        }
    }
}