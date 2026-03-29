const API_URL = '/api/todos';
const AUTH_URL = '/api/auth';

document.addEventListener('DOMContentLoaded', () => {
    // Auth UI Elements
    const authContainer = document.getElementById('auth-container');
    const mainContainer = document.getElementById('main-container');
    const authForm = document.getElementById('auth-form');
    const usernameInput = document.getElementById('auth-username');
    const passwordInput = document.getElementById('auth-password');
    const btnLogin = document.getElementById('btn-login');
    const btnSignup = document.getElementById('btn-signup');
    const authAlert = document.getElementById('auth-alert');
    const btnLogout = document.getElementById('btn-logout');
    const welcomeMessage = document.getElementById('welcome-message');

    // Todo UI Elements
    const todoForm = document.getElementById('todo-form');
    const todoTitleInput = document.getElementById('todo-title');
    const todoDescInput = document.getElementById('todo-desc');
    const todoList = document.getElementById('todo-list');
    const loadingState = document.getElementById('loading');
    const taskCount = document.getElementById('task-count');

    // Check auth status on load
    checkAuth();

    async function checkAuth() {
        try {
            const response = await fetch(`${AUTH_URL}/me`);
            if (response.ok) {
                const data = await response.json();
                showMainUI(data.username);
            } else {
                showAuthUI();
            }
        } catch (error) {
            showAuthUI();
        }
    }

    function showAuthUI() {
        authContainer.classList.remove('hidden');
        mainContainer.classList.add('hidden');
    }

    function showMainUI(username) {
        authContainer.classList.add('hidden');
        mainContainer.classList.remove('hidden');
        welcomeMessage.textContent = `Welcome, ${username}! Manage your tasks seamlessly.`;
        fetchTodos();
    }

    function showAlert(msg, isError = true) {
        authAlert.textContent = msg;
        authAlert.className = `alert ${isError ? 'error' : 'success'}`;
        authAlert.classList.remove('hidden');
        setTimeout(() => authAlert.classList.add('hidden'), 3000);
    }

    // Login logic
    authForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const username = usernameInput.value.trim();
        const password = passwordInput.value;
        if (username.length < 6 || password.length < 6) return showAlert('Username and password must be at least 6 characters');
        
        try {
            const response = await fetch(`${AUTH_URL}/login`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ username, password })
            });
            if (response.ok) {
                const data = await response.json();
                usernameInput.value = '';
                passwordInput.value = '';
                showMainUI(data.username);
            } else {
                showAlert('Invalid username or password');
            }
        } catch (error) {
            showAlert('Login failed');
        }
    });

    // Signup logic
    btnSignup.addEventListener('click', async () => {
        const username = usernameInput.value.trim();
        const password = passwordInput.value;
        
        if (!username || !password) return showAlert('Please fill out username and password fields first to signup');
        if (username.length < 6 || password.length < 6) return showAlert('Username and password must be at least 6 characters');

        try {
            const response = await fetch(`${AUTH_URL}/signup`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ username, password })
            });
            const textResponse = await response.text();
            if (response.ok) {
                showAlert('Signup successful! You can now login.', false);
                // Optionally auto login, but letting them click login is fine
            } else {
                showAlert(textResponse);
            }
        } catch (error) {
            showAlert('Signup failed');
        }
    });

    // Logout logic
    btnLogout.addEventListener('click', async () => {
        try {
            await fetch(`${AUTH_URL}/logout`, { method: 'POST' });
            showAuthUI();
            todoList.innerHTML = '';
            welcomeMessage.textContent = '';
        } catch (error) {
            console.error('Logout failed');
        }
    });

    // Todo Logic below
    todoForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const title = todoTitleInput.value.trim();
        const description = todoDescInput.value.trim();
        if (!title) return;
        
        try {
            const response = await fetch(API_URL, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ title, description })
            });

            if (response.ok) {
                const newTodo = await response.json();
                renderTodo(newTodo, true);
                todoTitleInput.value = '';
                todoDescInput.value = '';
                updateTaskCount();
            } else if (response.status === 401 || response.status === 403) {
                showAuthUI();
            } else {
                alert('Tạo công việc thất bại. Tiêu đề cần ít nhất 3 ký tự.');
            }
        } catch (error) {
            console.error('Error adding todo:', error);
        }
    });

    async function fetchTodos() {
        try {
            loadingState.style.display = 'block';
            const response = await fetch(API_URL);
            if (response.status === 401 || response.status === 403) {
                showAuthUI();
                return;
            }
            const todos = await response.json();
            
            loadingState.style.display = 'none';
            todoList.innerHTML = '';
            
            todos.forEach(todo => renderTodo(todo));
            updateTaskCount();
        } catch (error) {
            console.error('Error fetching todos:', error);
            loadingState.textContent = 'Failed to load tasks.';
        }
    }

    function renderTodo(todo, animate = false) {
        const li = document.createElement('li');
        li.className = `todo-item ${todo.completed ? 'completed' : ''}`;
        li.dataset.id = todo.id;
        
        li.innerHTML = `
            <div class="todo-content">
                <label class="checkbox-wrapper">
                    <input type="checkbox" ${todo.completed ? 'checked' : ''} onchange="toggleTodo(${todo.id}, this)">
                    <span class="checkmark"></span>
                </label>
                <div class="todo-text">
                    <div class="todo-title">${escapeHTML(todo.title)}</div>
                    ${todo.description ? `<div class="todo-desc">${escapeHTML(todo.description)}</div>` : ''}
                </div>
            </div>
            <div class="todo-actions">
                <button class="btn-icon" onclick="deleteTodo(${todo.id}, this)" title="Delete">
                    <i class="fas fa-trash"></i>
                </button>
            </div>
        `;

        if (animate) {
            todoList.prepend(li); // Add to top with animation
        } else {
            todoList.appendChild(li);
        }
    }

    window.toggleTodo = async (id, checkboxInfo) => {
        const item = checkboxInfo.closest('.todo-item');
        const isCompleted = checkboxInfo.checked;
        const title = item.querySelector('.todo-title').textContent;
        const descEl = item.querySelector('.todo-desc');
        const description = descEl ? descEl.textContent : '';

        // UI Optimistic Update
        if (isCompleted) item.classList.add('completed');
        else item.classList.remove('completed');
        
        try {
            const response = await fetch(`${API_URL}/${id}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ title, description, completed: isCompleted })
            });
            
            if (response.status === 401 || response.status === 403) return showAuthUI();
            if (!response.ok) {
                // Rollback
                checkboxInfo.checked = !isCompleted;
                item.classList.toggle('completed');
            }
        } catch (error) {
            checkboxInfo.checked = !isCompleted;
            item.classList.toggle('completed');
        }
    };

    window.deleteTodo = async (id, buttonInfo) => {
        const item = buttonInfo.closest('.todo-item');
        item.style.transform = 'translateX(20px)';
        item.style.opacity = '0';
        
        setTimeout(async () => {
            try {
                const response = await fetch(`${API_URL}/${id}`, {
                    method: 'DELETE'
                });
                
                if (response.status === 401 || response.status === 403) return showAuthUI();
                if (response.ok) {
                    item.remove();
                    updateTaskCount();
                } else {
                    item.style.transform = '';
                    item.style.opacity = '1';
                }
            } catch (error) {
                item.style.transform = '';
                item.style.opacity = '1';
            }
        }, 300);
    };

    function updateTaskCount() {
        const count = todoList.children.length;
        taskCount.textContent = `${count} task${count !== 1 ? 's' : ''}`;
    }

    function escapeHTML(str) {
        return str.replace(/[&<>'"]/g, 
            tag => ({ '&': '&amp;', '<': '&lt;', '>': '&gt;', "'": '&#39;', '"': '&quot;' }[tag] || tag)
        );
    }
});
