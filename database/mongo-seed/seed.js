db.createUser({
    user: "adminUser",
    pwd: "adminPass",
    roles : [
        { role: "readWrite", db: "recipes" },
        { role: "readWrite", db: "users"}
    ]
});

db = db.getSiblingDB('recipes');

db.recipes.insert([
    { name: 'Тестовый рецепт' }
]);

db = db.getSiblingDB('users');

db.users.insert([
    { name: 'Тестовый пользовател' }
]);
