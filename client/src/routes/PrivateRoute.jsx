import { Navigate } from "react-router-dom";
import { useUser } from "../context/UserContext";

const PrivateRoute = ({ children, role }) => {
  const { user } = useUser();

  if (!user || !user?.name || user?.name=="") {
    return <Navigate to="/" />; 
  }

  if (role && user.role !== role) {
    return <Navigate to="/home" />;
  }

  return children;
};

export default PrivateRoute;
