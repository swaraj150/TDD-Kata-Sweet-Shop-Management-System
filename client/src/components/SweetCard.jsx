import {
    Box,
    VStack,
    HStack,
    Text,
    Button,
    Tooltip,
    Menu,
    MenuButton,
    MenuList,
    MenuItem,
    IconButton,
    Flex
} from "@chakra-ui/react";
import { useEffect, useState } from "react";
import { FiMoreVertical } from "react-icons/fi";
import QuantityStepper from "./QuantityStepper";

const SweetCard = ({ sweet, onBuyClick, onUpdateClick, onDeleteClick, isAdmin }) => {
    const [quantity, setQuantity] = useState(1);
    const [buttonType, setButtonType] = useState("Buy");
    const [showMenu, setShowMenu] = useState(false);
    useEffect(() => {
        if (isAdmin) {
            setButtonType("Restock")
            setShowMenu(true);
        }
    }, [isAdmin])

    return (
        <Box
            key={sweet.id}
            borderWidth="1px"
            borderRadius="lg"
            p={4}
            boxShadow="md"
            maxW="full"
            height="175px"
            display="flex"
            alignItems="center"
            justifyContent="space-between"
        >
            <VStack spacing={2} align="start" flex="1" >

                <Flex align="center" justify="space-between" w="100%">
                    <Tooltip label={sweet.name} hasArrow>
                        <Text
                            fontWeight="bold"
                            fontSize="lg"
                            isTruncated
                            maxW="180px"
                        >
                            {sweet.name}
                        </Text>
                    </Tooltip>

                    <Menu>
                        <MenuButton
                            as={IconButton}
                            icon={<FiMoreVertical />}
                            variant="ghost"
                            size="sm"
                            disabled={!showMenu}
                        />
                        <MenuList>
                            <MenuItem onClick={() => onUpdateClick(sweet)}>Update Sweet</MenuItem>
                            <MenuItem onClick={() => onDeleteClick(sweet.id)} color="red.500">
                                Delete Sweet
                            </MenuItem>
                        </MenuList>
                    </Menu>
                </Flex>

                <Text color="gray.600">Category: {sweet.category}</Text>

                <HStack spacing={5} >
                    <Text fontWeight="semibold" color="teal.600">
                        ₹{sweet.price}
                    </Text>
                    <Text w="75px" fontWeight="semibold">Stock: {sweet.stockCount}</Text>
                    <QuantityStepper value={quantity} setValue={setQuantity} min={1} max={100} />
                </HStack>
                <Button colorScheme="teal" size="sm" onClick={() => onBuyClick(sweet.id, quantity, showMenu ? (1) : (-1))} isDisabled={buttonType === "Buy" ? sweet?.stockCount === 0 : false}
                >
                    {buttonType}
                </Button>
            </VStack>

            <VStack spacing={6}>




            </VStack>


        </Box>
    );
};

export default SweetCard;
