import { Box, HStack, Button, Spacer, Text, Heading } from "@chakra-ui/react";
import { useUser } from "../context/UserContext";
import { useNavigate } from "react-router-dom";

const Header = () => {
    const { user, setUser } = useUser();
    const navigate = useNavigate();
    const handleLogout = () => {
        localStorage.removeItem("user");
        setUser({ name: "", role: "" });
        localStorage.removeItem("jwtToken")
        navigate('/');
    };

    return (
        <Box p={4} bg="teal.500" color="white">
            <HStack>
                <Heading mb={2} textAlign="center">
                    Sweet Dashboard
                </Heading>
                <Spacer />
                {user?.name && user?.role === "ADMIN" && (
                    <Button colorScheme="teal" onClick={() => navigate("/sweet-form")}>
                        + Create Sweet
                    </Button>
                )}

                {user?.name && (
                    <Button colorScheme="teal" onClick={handleLogout}>
                        Logout
                    </Button>
                )}

            </HStack>
        </Box>
    );
};

export default Header;
