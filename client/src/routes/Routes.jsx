import SweetForm from "../components/SweetForm";
import SignInPage from "../components/SigninPage";
import SignUpPage from "../components/SignupPage";
import SweetDashboard from "../components/SweetDashboard";
import WelcomePage from "../components/WelcomePage";
import SweetFormWrapper from "../components/SweetFormWrapper";
import NotFoundPage from "../components/NotFoundPage";

export const publicRoutes = [
    {
        path: 'signin',
        element: <SignInPage />
    },
    {
        path: 'signup',
        element: <SignUpPage />
    },
    {
        path: '',
        element:<WelcomePage/>
    },
    
]

export const privateRoutes=[
    {
        path: 'sweet-form',
        element:<SweetFormWrapper/>,
        role:"ADMIN"
    },
    {
        path: '/home',
        element:<SweetDashboard/>
    },
    
]