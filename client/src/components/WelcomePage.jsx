import {
  Box,
  VStack,
  Heading,
  Text,
  Button,
  Stack,
  Image,
  useColorModeValue,
} from "@chakra-ui/react";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";

const WelcomePage = () => {
  const navigate = useNavigate();
  const bgGradient = useColorModeValue(
    "linear(to-r, teal.300, purple.300)",
    "linear(to-r, teal.600, purple.600)"
  );
  
  useEffect(()=>{
    const jwtToken=localStorage.getItem("jwtToken");
    if(jwtToken && jwtToken!=undefined){
      navigate("/home");
    }
  },[])
  return (
    <Box
      minH="100vh"
      display="flex"
      alignItems="center"
      justifyContent="center"
      bg={bgGradient}
      px={4}
    >
      <Box
        bg={useColorModeValue("white", "gray.700")}
        p={[6, 10]}
        borderRadius="xl"
        boxShadow="xl"
        maxW={["90%", "500px", "700px"]}
        w="full"
        textAlign="center"
      >
        <VStack spacing={[4, 6]}>
          <Image
            src="/logo.png" 
            alt="Sweet Logo"
            boxSize={["80px", "120px"]}
            mb={2}
          />

          <Heading size={["lg", "2xl"]} color={useColorModeValue("gray.800", "white")}>
            Welcome to SweetShop!
          </Heading>

          <Text fontSize={["sm", "md"]} color="gray.500">
            Manage your sweets, buy, or restock easily.
          </Text>

          <Stack direction={["column", "row"]} spacing={4} w="full" pt={4}>
            <Button
              colorScheme="teal"
              variant="solid"
              flex="1"
              _hover={{ transform: "scale(1.05)" }}
              onClick={() => navigate("/signin")}
            >
              Sign In
            </Button>
            <Button
              colorScheme="teal"
              variant="outline"
              flex="1"
              _hover={{ transform: "scale(1.05)", bg: "teal.100" }}
              onClick={() => navigate("/signup")}
            >
              Sign Up
            </Button>
          </Stack>
        </VStack>
      </Box>
    </Box>
  );
};

export default WelcomePage;
