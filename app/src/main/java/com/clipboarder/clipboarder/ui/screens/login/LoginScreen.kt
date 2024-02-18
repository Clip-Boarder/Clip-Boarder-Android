package com.clipboarder.clipboarder.ui.screens.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.clipboarder.clipboarder.BuildConfig
import com.clipboarder.clipboarder.R
import com.clipboarder.clipboarder.ui.screens.main.MAIN_SCREEN
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException


/**
 * Route for the login screen.
 */
const val LOGIN_SCREEN = "login_screen"

/**
 * Login screen.
 *
 * This screen facilitates user login, featuring input fields for entering
 * credentials and options for authentication. It's the gateway for users
 * to access their personal Clipboarder accounts and content.
 *
 * @param navController The navigation controller for navigating between screens.
 * @since 1.0.0
 * @author YoungJin Sohn
 */
@Composable
fun LoginScreen(navController: NavController, viewModel: LoginScreenViewModel = hiltViewModel()) {
    val context: Context = LocalContext.current
    val isLoginSuccess by viewModel.isLoginSuccess.collectAsState(initial = null)
    val isLoginProcessing by viewModel.isLoginProcessing.collectAsState(initial = false)

    val (googleSignInClient, signInLauncher) = rememberGoogleSignInSetup(context, viewModel)

    LaunchedEffect(key1 = true) {
        val alreadySignedInAccount = GoogleSignIn.getLastSignedInAccount(context)
        if (alreadySignedInAccount != null) {
            viewModel.startLoginProcess()
            viewModel.signInWithGoogleAccount(alreadySignedInAccount)
        } else {
            googleSignInClient.silentSignIn().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val account = task.result
                    viewModel.signInWithGoogleAccount(account)
                }
            }
        }
    }

    LaunchedEffect(key1 = isLoginSuccess) {
        when (isLoginSuccess) {
            true -> {
                viewModel.endLoginProcess()
                navController.navigate(MAIN_SCREEN) {
                    popUpTo(LOGIN_SCREEN) { inclusive = true }
                }
            }

            false -> {
                viewModel.endLoginProcess()
                Toast.makeText(context, "로그인 중 문제가 발생했습니다. 나중에 다시 시도해주세요.: 1", Toast.LENGTH_SHORT)
                    .show()
            }

            else -> {

            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LoginScreenContent(isLoginProcessLoading = isLoginProcessing) {
            viewModel.startLoginProcess()
            signInLauncher.launch(googleSignInClient.signInIntent)
        }
    }
}

@Composable
fun rememberGoogleSignInSetup(
    context: Context,
    viewModel: LoginScreenViewModel
): Pair<GoogleSignInClient, ActivityResultLauncher<Intent>> {
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(BuildConfig.clipboarderServerClientId)
        .requestEmail()
        .requestProfile()
        .build()

    val googleSignInClient = GoogleSignIn.getClient(context, gso)
    val signInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                viewModel.signInWithGoogleAccount(account)
            } catch (e: ApiException) {
                Toast.makeText(context, "로그인 중 문제가 발생했습니다. 나중에 다시 시도해주세요.: 3", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            Toast.makeText(context, "로그인 중 문제가 발생했습니다. 나중에 다시 시도해주세요.: 4", Toast.LENGTH_SHORT)
                .show()
        }
    }
    return Pair(googleSignInClient, signInLauncher)
}

@Composable
fun LoginScreenContent(
    isLoginProcessLoading: Boolean,
    onGoogleSignInRequested: () -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BrandingSection(modifier = Modifier.weight(1f))
        ActionSection(onGoogleSignInRequested = onGoogleSignInRequested)
    }

    if (isLoginProcessLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0x77000000))
                .pointerInput(Unit) {},
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun BrandingSection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(144.dp),
            painter = painterResource(id = R.drawable.clipboarder_image),
            contentDescription = "클립보더 이미지"
        )
        Text(
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontSize = 36.sp, letterSpacing = (-0.1).em)) {
                    append("더 ")
                }
                append("빠르게, ")
                withStyle(style = SpanStyle(fontSize = 36.sp, letterSpacing = (-0.1).em)) {
                    append("더 ")
                }
                append("편리하게")
            },
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun ActionSection(onGoogleSignInRequested: () -> Unit) {
    Text("지금 바로 시작해보세요", fontSize = 15.sp, fontWeight = FontWeight.Medium)
    Spacer(modifier = Modifier.padding(12.dp))
    Divider(
        color = Color(0xFF5E5E5E),
        thickness = 2.dp,
        modifier = Modifier.fillMaxWidth(1.0f)
    )
    Spacer(modifier = Modifier.padding(12.dp))
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
        onClick = onGoogleSignInRequested,
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
        ),
    ) {
        Row(
            modifier = Modifier.padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = com.google.android.gms.auth.api.R.drawable.googleg_standard_color_18),
                contentDescription = "구글 아이콘",
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 8.dp),
                tint = Color.Unspecified
            )
            Text(
                "구글 계정으로 시작하기",
                color = Color(0xff767676),
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}