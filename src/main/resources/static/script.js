function initializePage() {
    populateUserDropdown();
    setDefaultPreferences();
}

function populateUserDropdown() {
    // Example: Fetch user list from backend
    fetch('/preferences/allUsers') // Adjust with your actual endpoint
        .then(response => response.json())
        .then(users => {
            const dropdown = document.getElementById('userDropdown');
            users.forEach(user => {
                const option = document.createElement('option');
                option.value = user.id;
                option.textContent = user.name; // Adjust based on your user object
                dropdown.appendChild(option);
            });
        });
}

function loadUserPreferences() {
    const userId = document.getElementById('userDropdown').value;
    if (userId) {
        fetch(`/preferences/${userId}`)
            .then(response => response.json())
            .then(preferences => {
                // Populate form fields
                document.getElementById('shortBreakInterval').value = preferences.shortBreakReminderInterval;
                document.getElementById('longBreakInterval').value = preferences.longBreakReminderInterval;
                document.getElementById('waterReminderInterval').value = preferences.waterReminderInterval;
                document.getElementById('foodReminderInterval').value = preferences.foodReminderInterval;
                startUpdatingTimeRemaining(userId);
                updateTotalWaterDrank();
                markAsCompleted('shortBreak');
                markAsCompleted('longBreak');
                markAsCompleted('waterBreak');
                markAsCompleted('foodBreak');
            });
    } else {
        setDefaultPreferences();
    }
}

function setDefaultPreferences() {
    document.getElementById('shortBreakInterval').value = 240;
    document.getElementById('longBreakInterval').value = 240;
    document.getElementById('waterReminderInterval').value = 240;
    document.getElementById('foodReminderInterval').value = 240;
}

function setShortBreakPreferences() {
    const userId = document.getElementById('userDropdown').value;
    const preferences = {
        shortBreakReminderInterval: document.getElementById('shortBreakInterval').value,
    };

    fetch(`/preferences/setShortBreak/${userId}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(preferences)
    }).then(response => {
        if (response.ok) {
            alert("Preferences updated successfully");
        }
    });
}

function setLongBreakPreferences() {
    const userId = document.getElementById('userDropdown').value;
    const preferences = {
        longBreakReminderInterval: document.getElementById('longBreakInterval').value,
    };

    fetch(`/preferences/setLongBreak/${userId}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(preferences)
    }).then(response => {
        if (response.ok) {
            alert("Preferences updated successfully");
        }
    });
}

function setWaterPreferences() {
    const userId = document.getElementById('userDropdown').value;
    const preferences = {
        waterReminderInterval: document.getElementById('waterReminderInterval').value,
    };

    fetch(`/preferences/setWaterBreak/${userId}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(preferences)
    }).then(response => {
        if (response.ok) {
            alert("Preferences updated successfully");
        }
    });
}

function setFoodPreferences() {
    const userId = document.getElementById('userDropdown').value;
    const preferences = {
        foodReminderInterval: document.getElementById('foodReminderInterval').value,
    };

    fetch(`/preferences/setFoodBreak/${userId}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(preferences)
    }).then(response => {
        if (response.ok) {
            alert("Preferences updated successfully");
        }
    });
}

function logWater() {
    const userId = document.getElementById('userDropdown').value;
    const water = {
        waterAmount: document.getElementById('waterAmount').value,
    };

    fetch(`/water/${userId}`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(water)
    }).then(response => {
        if (response.ok) {

            alert("Water intake logged successfully");
            updateTotalWaterDrank();
        }
    });

}

function addUser() {
    const userName = document.getElementById('newUserName').value;
    if (userName) {
        fetch('/preferences/', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ name: userName })
        }).then(response => {
            if (response.ok) {
                alert("New user added successfully");
                populateUserDropdown(); // Refresh the user list
            }
        });
    } else {
        alert("Please enter a name for the new user.");
    }
}

function updateTotalWaterDrank() {
    const userId = document.getElementById('userDropdown').value;
    fetch(`/water/totalWater/${userId}`) // Adjust with your actual endpoint
        .then(response => response.json())
        .then(totalWater => {
            console.log("Total water drank fetched:", totalWater); // Debug log
            document.getElementById('totalWaterDrank').textContent = totalWater;
        })
        .catch(error => console.error('Error fetching total water:', error));
}


function markAsCompleted(type) {
    const id = document.getElementById('userDropdown').value;
    switch (type) {
        case 'shortBreak':
            fetch(`/preferences/lastShortBreak/${id}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' }
            }).then(response => {
                if (response.ok) {
                    alert(`${type} marked as completed`);
                    // Reset the time remaining and update display
                    document.getElementById(`${type}TimeRemaining`).textContent = 'Interval reset';
                }
            });
            break;
        case 'longBreak':
            fetch(`/preferences/lastLongBreak/${id}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' }
            }).then(response => {
                if (response.ok) {
                    alert(`${type} marked as completed`);
                    // Reset the time remaining and update display
                    document.getElementById(`${type}TimeRemaining`).textContent = 'Interval reset';
                }
            });
            break;
        case 'waterBreak':
            fetch(`/preferences/lastWaterBreak/${id}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' }
            }).then(response => {
                if (response.ok) {
                    alert(`${type} marked as completed`);
                    // Reset the time remaining and update display
                    document.getElementById(`${type}TimeRemaining`).textContent = 'Interval reset';
                }
            });
            break;
        case 'foodBreak':
            fetch(`/preferences/lastFoodBreak/${id}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' }
            }).then(response => {
                if (response.ok) {
                    alert(`${type} marked as completed`);
                    // Reset the time remaining and update display
                    document.getElementById(`${type}TimeRemaining`).textContent = 'Interval reset';
                }
            });
            break;
    }

}

function updateTimeRemaining(type) {
    const userId = document.getElementById('userDropdown').value;
    fetch(`/preferences/timeRemaining/${userId}/${type}`)
        .then(response => response.json())
        .then(timeRemaining => {
            let elementId;
            switch(type) {
                case 'short':
                    elementId = 'shortTimeRemaining';
                    break;
                case 'long':
                    elementId = 'longTimeRemaining';
                    break;
                case 'water':
                    elementId = 'waterTimeRemaining';
                    break;
                case 'food':
                    elementId = 'foodTimeRemaining';
                    break;
                default:
                    console.error(`Unknown type: ${type}`);
                    return;
            }
            document.getElementById(elementId).textContent = `${timeRemaining} minutes remaining`;
        })
        .catch(error => console.error('Error fetching time remaining:', error));
}


function startUpdatingTimeRemaining(userId) {
    const types = ['short', 'long', 'water', 'food'];
    types.forEach(type => {
        updateTimeRemaining(type);
    });
    types.forEach(type => {
        setInterval(() => updateTimeRemaining(type), 60000); // Update every minute
    });
}