import {
  Box,
  Button,
  FormControl,
  FormErrorMessage,
  FormLabel,
  Input,
  Select,
  VStack,
  Heading,
} from "@chakra-ui/react";
import { useForm } from "react-hook-form";

const SweetForm = ({ data = {}, onSubmit, isSubmitting }) => {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm({
    defaultValues: {
      name: data.name || "",
      category: data.category || "",
      price: data.price || "",
      stockCount: data.stockCount || "",
    },
  });

  return (
    <Box
      maxW="md"
      mx="auto"
      mt={5}
      p={6}
      borderWidth={1}
      borderRadius="lg"
      boxShadow="lg"
    >
      <Heading size="md" textAlign="center" mb={6}>
        {data.id ? "Update Sweet" : "Create Sweet"}
      </Heading>

      <form onSubmit={handleSubmit(onSubmit)}>
        <VStack spacing={4}>
          <FormControl isInvalid={errors.name}>
            <FormLabel>Name</FormLabel>
            <Input
              placeholder="Sweet Name"
              {...register("name", { required: "Name is required" })}
            />
            <FormErrorMessage>{errors.name?.message}</FormErrorMessage>
          </FormControl>

          <FormControl isInvalid={errors.category}>
            <FormLabel>Category</FormLabel>
            <Select
              placeholder="Select category"
              {...register("category", { required: "Category is required" })}
            >
              <option value="Indian">Indian</option>
              <option value="Western">Western</option>
              <option value="Bakery">Bakery</option>
            </Select>
            <FormErrorMessage>{errors.category?.message}</FormErrorMessage>
          </FormControl>

          <FormControl isInvalid={errors.price}>
            <FormLabel>Price</FormLabel>
            <Input
              type="number"
              placeholder="Price"
              {...register("price", { required: "Price is required" })}
            />
            <FormErrorMessage>{errors.price?.message}</FormErrorMessage>
          </FormControl>

          <FormControl isInvalid={errors.stockCount}>
            <FormLabel>Stock Count</FormLabel>
            <Input
              type="number"
              placeholder="Stock Count"
              {...register("stockCount", {
                required: "Stock count is required",
              })}
            />
            <FormErrorMessage>{errors.stockCount?.message}</FormErrorMessage>
          </FormControl>

          <Button
            mt={4}
            colorScheme="teal"
            isLoading={isSubmitting}
            type="submit"
            w="full"
          >
            {data.id ? "Update" : "Create"}
          </Button>
        </VStack>
      </form>
    </Box>
  );
};

export default SweetForm;
