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


# Tutorial rápido para usar Git en NetBeans

## 1) Instalar lo necesario

### **1. Instalar Git**

1. Descargar Git desde su sitio oficial.
2. Instalar con las opciones por defecto.

### **2. Instalar NetBeans**

1. Descargar NetBeans desde su página oficial.
2. Instalar normalmente.

*(NetBeans ya incluye soporte Git, no se necesita extensión.)*

---

# 2) Clonar el repositorio

En NetBeans:

1. Menú **Team → Git → Clone**.
2. Pegar la URL del repositorio (HTTPS).
3. Ingresar credenciales si lo pide (GitHub).
4. Elegir carpeta donde se guardará el proyecto.
5. Finalizar y **Open Project**.

---

# 3) Configurar tu usuario de Git (solo una vez)

Si Git nunca ha sido configurado en tu equipo:

Abrir una terminal (externa o integrada) y ejecutar:

```
git config --global user.name "Tu Nombre"
git config --global user.email "tu_correo@example.com"
```

---

# 4) Flujo básico de trabajo en NetBeans

### **1. Obtener los cambios del repositorio (Pull)**

Menú: **Team → Git → Pull**
o clic derecho en el proyecto → **Git → Pull**

---

### **2. Ver qué archivos cambiaron**

Los cambios aparecen en color en el panel de proyectos:

* Azul = modificado
* Verde = nuevo
* Rojo = eliminado

También puedes verlos con clic derecho → **Git → Show Changes**.

---

### **3. Agregar cambios (Stage)**

NetBeans maneja el “add” de forma visual:

1. Clic derecho sobre el archivo o carpeta → **Git → Add**
   (o se hace automáticamente al preparar un commit).

---

### **4. Crear el commit**

Clic derecho en el proyecto → **Git → Commit**
Aparecerá una ventana donde:

* Escribes el mensaje de commit
* Seleccionas qué archivos incluir
* Puedes ver diferencias (*diff*)

Luego presionas **Commit**.

---

### **5. Enviar los cambios (Push)**

Clic derecho → **Git → Remote → Push**

---

# 5) Flujo típico día a día

1. **Pull** antes de empezar
2. Hacer cambios en el proyecto
3. **Git → Commit** (NetBeans se encarga del “add”)
4. **Push** para enviar tus cambios al repo

---

# 6) Comandos / funciones útiles en NetBeans

### Ver cambios:

**Git → Show Changes**

### Ver historial:

**Git → Show History**

### Crear nueva rama:

**Git → Branch → Create**

### Cambiar de rama:

**Git → Branch → Switch To**

### Resolver conflictos:

Al hacer Pull, si hay conflictos NetBeans abrirá un editor especial para resolverlos.

---


