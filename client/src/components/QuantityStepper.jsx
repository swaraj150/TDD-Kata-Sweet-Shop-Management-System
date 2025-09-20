import { HStack, IconButton, Input } from "@chakra-ui/react";
import { FiPlus, FiMinus } from "react-icons/fi";

const QuantityStepper = ({ value, setValue, min = 1, max = 100 }) => {
    const decrement = () => setValue(Math.max(min, value - 1));
    const increment = () => setValue(Math.min(max, value + 1));

    return (
        <HStack >
            <IconButton
                icon={<FiMinus />}
                size="sm"
                onClick={decrement}
                aria-label="Decrease quantity"
            />
            <Input
                value={value}
                onChange={(e) => {
                    const val = parseInt(e.target.value) || min;
                    setValue(Math.max(min, Math.min(max, val)));
                }}
                size="sm"
                width="40px"
                textAlign="center"
            />
            <IconButton
                icon={<FiPlus />}
                size="sm"
                onClick={increment}
                aria-label="Increase quantity"
                mr={2}
            />
        </HStack>
    );
};

export default QuantityStepper;
