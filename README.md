# Riasa
Kaiser gei AHVA

## 1) Instalar lo necesario

### **1. Instalar Git**

1. Ir al sitio oficial de Git.
2. Descargar la versión para su sistema operativo.
3. Instalar con las opciones por defecto.

### **2. Instalar Visual Studio Code**

1. Entrar al sitio oficial de VS Code.
2. Descargar e instalar.
3. Abrir VS Code.

### **3. Extensión recomendada**

Dentro de VS Code:

1. Abrir la pestaña **Extensions** (icono de cuadrito).
2. Buscar **“GitLens”**.
3. Instalarla (esto ayuda a visualizar cambios y commits).

---

## 2) Clonar el repositorio

1. Copiar la URL del repo desde GitHub (botón **Code → HTTPS**).
2. En VS Code:

   * Abrir la **Command Palette** (Ctrl+Shift+P).
   * Escribir: **Git: Clone**
   * Pegar la URL.
3. Elegir carpeta donde se guardará el proyecto.
4. Abrir ese folder en VS Code.

---

## 3) Configurar tu usuario en Git (solo la primera vez)

En una terminal dentro de VS Code:

```
git config --global user.name "Tu Nombre"
git config --global user.email "tu_correo@example.com"
```

---

## 4) Flujo básico de trabajo

### **1. Obtener los cambios del repositorio**

Antes de trabajar:

```
git pull
```

### **2. Ver qué archivos cambiaron**

```
git status
```

### **3. Agregar archivos modificados**

Agregar todo lo que cambió:

```
git add .
```

O agregar un archivo específico:

```
git add ruta/del/archivo
```

### **4. Crear un commit**

```
git commit -m "Descripción breve del cambio"
```

### **5. Enviar los cambios al repositorio**

```
git push
```

---

## 5) Flujo típico del día a día

1. **git pull** (traer lo más reciente antes de trabajar)
2. Hacer cambios en el código
3. **git add .**
4. **git commit -m "texto del cambio"**
5. **git push**

---

## 6) Comandos útiles adicionales

### Ver historial de commits:

```
git log
```

### Cambiar de rama:

```
git checkout nombre_de_rama
```

### Crear una nueva rama:

```
git checkout -b nueva_rama
```

---

Si quieres, te lo preparo también en archivo descargable o en formato para enviar por correo.

