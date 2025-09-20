// NotFound.js
import { Box, Heading, Text, Button, VStack } from "@chakra-ui/react";
import { useNavigate } from "react-router-dom";

const NotFoundPage = () => {
  const navigate = useNavigate();

  return (
    <Box
      textAlign="center"
      py={20}
      px={6}
      display="flex"
      justifyContent="center"
      alignItems="center"
      height="100vh"
    >
      <VStack spacing={6}>
        <Heading
          display="inline-block"
          as="h1"
          size="4xl"
          bgGradient="linear(to-r, teal.400, teal.600)"
          backgroundClip="text"
        >
          404
        </Heading>
        <Text fontSize="2xl" mt={3}>
          Page Not Found
        </Text>
        <Text color="gray.500">
          The page you’re looking for doesn’t seem to exist.
        </Text>

        <Button
          colorScheme="teal"
          variant="solid"
          onClick={() => {
            const jwtToken=localStorage.getItem("jwtToken");
            if(jwtToken && jwtToken!=undefined){
                navigate("/home");
            }else{
                navigate("/")
            }
            
        }}
        >
          Go Home
        </Button>
      </VStack>
    </Box>
  );
};

export default NotFoundPage;
