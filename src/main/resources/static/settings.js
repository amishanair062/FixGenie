// settings.js
document.addEventListener("DOMContentLoaded", function () {
    applySavedSettings();
});

function applySavedSettings() {
    const theme = localStorage.getItem("theme") || "Light";
    const emoji = localStorage.getItem("emoji") || "🧞‍♂️";
    const mode = localStorage.getItem("genie-mode") || "🧙 Wise";
    const username = localStorage.getItem("username") || "Guest";

    // Update theme
    if (theme === "Dark") {
        document.body.classList.add("dark-mode");
    } else {
        document.body.classList.remove("dark-mode");
    }

    // Update username
    const usernameDisplay = document.getElementById("display-username");
    if (usernameDisplay) {
        usernameDisplay.textContent = username;
    }

    // Update emoji
    const emojiDisplay = document.getElementById("selected-emoji");
    if (emojiDisplay) {
        emojiDisplay.textContent = `You selected: ${emoji}`;
    }

    // You can also use the mode in AI responses or avatars if needed
    console.log("🧠 Genie Personality:", mode);
}
