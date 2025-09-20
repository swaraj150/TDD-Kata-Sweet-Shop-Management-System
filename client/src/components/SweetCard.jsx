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
} from "@chakra-ui/react";
import { FiMoreVertical } from "react-icons/fi";

const SweetCard = ({ sweet, onBuyClick, onUpdateClick, onDeleteClick,showMenu  }) => {

    return (
        <Box
            key={sweet.id}
            borderWidth="1px"
            borderRadius="lg"
            p={4}
            boxShadow="md"
            maxW="full"
            height="140px"
            display="flex"
            alignItems="center"
            justifyContent="space-between"
        >
            <VStack spacing={1} align="start" flex="1">
                <Tooltip label={sweet.name} hasArrow>
                    <Text fontWeight="bold" fontSize="lg" isTruncated maxW="180px">
                        {sweet.name}
                    </Text>
                </Tooltip>
                <Text color="gray.600">Category: {sweet.category}</Text>

                <HStack spacing={4}>
                    <Text fontWeight="semibold" color="teal.600">
                        ₹{sweet.price}
                    </Text>
                    <Text fontWeight="semibold">Stock: {sweet.stockCount}</Text>
                </HStack>
            </VStack>

            <VStack spacing={5}>
                {showMenu && (
                    <Menu>
                        <MenuButton
                            as={IconButton}
                            icon={<FiMoreVertical />}
                            variant="ghost"
                            size="sm"
                        />
                        <MenuList>
                            <MenuItem onClick={()=>onUpdateClick(sweet.id)}>Update Sweet</MenuItem>
                            <MenuItem onClick={()=>onDeleteClick(sweet.id)} color="red.500">Delete Sweet</MenuItem>
                            
                        </MenuList>
                    </Menu>
                )}
                <Button colorScheme="teal" size="sm" onClick={()=>onBuyClick(sweet.id)}>
                    Buy
                </Button>
            </VStack>
        </Box>
    );
};

export default SweetCard;
