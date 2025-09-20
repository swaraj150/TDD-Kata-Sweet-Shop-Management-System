import { useLocation, useNavigate } from "react-router-dom";
import SweetForm from "./SweetForm";
import { useState } from "react";
import sweetApi from "../api/modules/sweet.api";
import { toast } from 'react-toastify'

const SweetFormWrapper = () => {
    const location = useLocation();
    const navigate = useNavigate();
    const sweet = location.state?.sweet || {};
    const [isSubmitting, setIsSubmitting] = useState(false);

    const handleSubmit = async (formData) => {
        setIsSubmitting(true);
        if (sweet?.id) {
            const { res, err } = await sweetApi.update({id:sweet?.id,...formData});
            if (res) {
                toast.success("Sweet updated!");
            }
            if (err) toast.error(typeof err === 'string' ? err : 'An error occurred. Please try again.')
            setIsSubmitting(false);

        } else {
            const { res, err } = await sweetApi.create(formData);
            if (res) {
                toast.success("Sweet created!");
            }
            if (err) toast.error(typeof err === 'string' ? err : 'An error occurred. Please try again.')
            setIsSubmitting(false);
        }
        navigate('/home')
    };

    return <SweetForm data={sweet} onSubmit={handleSubmit} isSubmitting={isSubmitting} />;
};

export default SweetFormWrapper;
