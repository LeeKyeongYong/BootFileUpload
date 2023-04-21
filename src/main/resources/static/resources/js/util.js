const isNull = (src, dest, msg) => {
    if (!src.value) {
        src.classList.add("error-border");
        dest.textContent = msg;
        return true;
    } else {
        src.classList.remove("error-border");
        dest.textContent = "";
        return false;
    }
};

const isEqual = (src1, src2, dest, msg) => {
    if (src1.value === src2.value) {
        src2.classList.remove("error-border");
        dest.textContent = "";
        return true;
    } else {
        src2.classList.add("error-border");
        dest.textContent = msg;
        return false;
    }
};