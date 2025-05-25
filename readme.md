
# 📚 Library Management System – Fullstack Project

Repositori ini merupakan gabungan dari tiga bagian sistem:

1. **Backend** menggunakan Spring Boot (Java)
2. **Frontend** menggunakan Next.js
3. **Algoritma** sebagai modul logika terpisah

---

## 🛠️ 1. Backend - Spring Boot

### ✅ Cara Menjalankan

1. **Instalasi Database PostgreSQL**
   ```bash
   docker compose up -d
   ```

2. **Menjalankan Backend**
    - Jalankan aplikasi Spring Boot seperti biasa (`Run` via IDE atau `mvn spring-boot:run`)
    - Migrasi database akan berjalan otomatis menggunakan **Flyway Migration**

3. **Akses API Dokumentasi (Swagger)**
    - Buka: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

4. **Troubleshooting**
   Jika muncul error berikut saat build:
   ```
   java: variable borrowBookUsecase not initialized in the default constructor
   ```
   **Solusi**:
    - Masuk ke `Settings` > `Build, Execution, Deployment` > `Compiler` > `Annotation Processors`
    - Centang `Obtain processor from project classpath`
    - Pastikan `Annotation profile for librarian` aktif

---

## 🌐 2. Frontend - Next.js

### ✅ Cara Menjalankan

```bash
npm install
npm run dev
```

Akses aplikasi melalui: [http://localhost:3000](http://localhost:3000)

---

## 🧠 3. Algoritma - JavaScript/TypeScript

### ✅ Cara Menjalankan

```bash
npm install
npm run dev
```