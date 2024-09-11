
export const UserInput = ({ type, placeholder, value, name, onChange }) => {
    return (
        <input
            className="user-input"
            type={type}
            placeholder={placeholder}
            onChange={onChange}
            value={value}
            name={name}
        />
    );
};
