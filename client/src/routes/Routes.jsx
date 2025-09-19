import CreateSweetPage from "../components/CreateSweetPage";
import SignInPage from "../components/SigninPage";
import SignUpPage from "../components/SignupPage";

export const publicRoutes = [
    {
        path: 'sign-in',
        element: <SignInPage />
    },
    {
        path: 'sign-up',
        element: <SignUpPage />
    },

]