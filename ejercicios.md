# 10 ejercicios de pruebas unitarias (Java + JUnit 5)

**Condiciones generales**
- No usar mocks (ni Mockito ni similares).
- No usar IO (sin ficheros, sin red, sin BD). Todo debe ser lógica pura y determinista.
- No implementar el código de producción en este documento: aquí solo se describen los ejercicios y los tests a escribir.
- Escribir los tests con **JUnit 5** usando `@Test` (sin tests parametrizados). Puedes usar `@BeforeEach` si te ayuda.

---

## 1) Normalizador de nombre completo

**Objetivo**: Probar un método que normaliza nombres y apellidos recibidos como texto.

**Reglas**
- Entrada: `String`.
- Salida: `String` con:
  - `trim()` aplicado.
  - Múltiples espacios internos colapsados a uno.
  - Cada palabra en formato *Capitalized* (primera letra mayúscula, resto minúsculas).
- Si `raw` es `null`: lanzar `IllegalArgumentException`.
- Si tras normalizar queda vacío: lanzar `IllegalArgumentException`.

**Checklist de tests**
- Devuelve el mismo resultado para un nombre ya normalizado.
- Recorta espacios al inicio y al final.
- Colapsa múltiples espacios internos.
- Convierte `"mARiA jOSE"` a `"Maria Jose"`.
- Mantiene palabras con una sola letra (`"a b"` → `"A B"`).
- Lanza excepción con `null`.
- Lanza excepción con `"   "` (solo espacios).

---

## 2) Validador de contraseña

**Objetivo**: Probar un método `isValid(String password)` (o equivalente) que valida una contraseña.

**Reglas**
- Debe cumplir:
  - Longitud mínima 8.
  - Al menos 1 mayúscula.
  - Al menos 1 minúscula.
  - Al menos 1 dígito.
  - Al menos 1 carácter no alfanumérico (símbolo).
- `null` devuelve `false` (o lanza excepción: elige una política y sé consistente; los tests deben reflejarla).

**Checklist de tests**
- Acepta una contraseña que cumple todas las reglas.
- Rechaza por longitud insuficiente.
- Rechaza si falta mayúscula.
- Rechaza si falta minúscula.
- Rechaza si falta dígito.
- Rechaza si falta símbolo.
- Caso borde: exactamente 8 caracteres y cumple reglas.
- Caso con espacios: define si se permiten; testea el comportamiento esperado.

---

## 3) Calculadora de precio final con descuento e IVA

**Objetivo**: Probar un método que calcula el precio final a partir de:
- `basePrice` (importe base),
- `discountPercent` (0–100),
- `vatPercent` (0–100),
aplicando primero descuento y luego IVA.

**Reglas**
- No se permiten importes negativos.
- `discountPercent` y `vatPercent` deben estar en [0, 100].
- La salida se redondea a 2 decimales (define claramente si es HALF_UP u otra estrategia; los tests deben fijarlo).

**Checklist de tests**
- Caso nominal con descuento y con IVA.
- Sin descuento (0%).
- Sin IVA (0%).
- Descuento 100% ⇒ resultado 0 (independientemente de IVA).
- Lanza excepción con `basePrice < 0`.
- Lanza excepción con `discountPercent < 0` o `> 100`.
- Lanza excepción con `vatPercent < 0` o `> 100`.
- Prueba de redondeo: valor que obliga a redondear (p. ej. termina en 3er decimal 5).

---

## 4) Conversor de números romanos a enteros

**Objetivo**: Probar un método `toInt(String roman)` que convierte números romanos a `int`.

**Reglas**
- Acepta romanos en el rango 1..3999.
- Soporta notación sustractiva estándar: IV, IX, XL, XC, CD, CM.
- Rechaza formas inválidas (p. ej. `"IIII"`, `"VV"`, `"IC"`, `"XM"`).
- `null` o vacío debe lanzar `IllegalArgumentException`.

**Checklist de tests**
- Convierte correctamente valores básicos: I, V, X, L, C, D, M.
- Convierte valores con sustracción: IV=4, IX=9, XL=40, XC=90, CD=400, CM=900.
- Convierte un valor compuesto típico: `"MCMXCIV"` = 1994.
- Rechaza `null`.
- Rechaza `""`.
- Rechaza `"IIII"`.
- Rechaza `"IC"`.
- Caso borde: `"MMMCMXCIX"` = 3999.

---

## 5) Validador de ISBN-10

**Objetivo**: Probar un método `isValidIsbn10(String isbn)`.

**Reglas**
- Acepta 10 caracteres: 9 dígitos + dígito de control.
- El dígito de control puede ser `X` (representa 10).
- Puede aceptar guiones/espacios y normalizarlos.
- Cualquier otro formato es inválido.

**Checklist de tests**
- ISBN-10 válido con dígito de control numérico.
- ISBN-10 válido con `X` como control.
- Rechaza longitud incorrecta tras normalizar.
- Rechaza caracteres no permitidos (letras distintas de X en control, símbolos inesperados).
- Rechaza checksum incorrecto (misma longitud, pero control mal).
- Comportamiento con `null`.
- Si ignoras guiones/espacios: un ISBN válido con guiones debe validarse.

---

## 6) Parser simple de CSV (una línea)

**Objetivo**: Probar un método `parseLine(String line)` que convierte una línea CSV en una lista de campos.

**Reglas**
- Separador: coma `,`.
- Soporta campos entre comillas dobles `"..."`.
- Dentro de comillas, una comilla escapada se representa como `""`.
- No hay saltos de línea (es una sola línea en memoria).
- `null` debe lanzar `IllegalArgumentException`.

**Checklist de tests**
- Línea sin comillas: `"a,b,c"` → 3 campos.
- Campos vacíos: `"a,,c"` → campo intermedio vacío.
- Campo con coma dentro de comillas: `"a,\"b,c\",d"`.
- Campo con comillas escapadas: `"a,\"b\"\"c\",d"` (según la regla de `""`).
- Espacios alrededor de separadores: define si se preservan o se recortan; testea.
- Rechaza comillas sin cerrar.
- Rechaza entrada `null`.

---

## 7) Clasificador de triángulos

**Objetivo**: Probar un método `classify(a, b, c)` que devuelve el tipo de triángulo.

**Reglas**
- Entradas: tres longitudes (int o double) estrictamente positivas.
- Debe cumplir desigualdad triangular.
- Salida posible: `EQUILATERO`, `ISOCELES`, `ESCALENO` (o strings equivalentes).
- Si no es triángulo válido: lanzar `IllegalArgumentException`.

**Checklist de tests**
- Equilátero: (5,5,5).
- Isósceles: (5,5,3) y permutaciones (para comprobar simetría).
- Escaleno: (4,5,6).
- Rechaza lado 0.
- Rechaza lado negativo.
- Rechaza desigualdad triangular: (1,2,3) (caso borde) y (1,2,10).
- Si usas `double`: prueba con valores muy cercanos al borde y define tolerancia si corresponde.

---

## 8) Detector de palíndromos con normalización

**Objetivo**: Probar un método `isPalindrome(String text)`.

**Reglas**
- Ignora espacios y signos de puntuación.
- Ignora mayúsculas/minúsculas.
- Define si `null` es `false` o excepción; testea.
- Define qué haces con cadenas vacías (habitualmente `true`, pero decide y testea).

**Checklist de tests**
- Palíndromo simple: `"ana"`.
- Palíndromo con espacios y mayúsculas: `"An a"`.
- Palíndromo con puntuación: `"¿Acaso hubo búhos acá?"`.
- No palíndromo: `"casa"`.
- Solo signos/espacios: define el resultado esperado y testea.
- Comportamiento con `""`.
- Comportamiento con `null`.

---

## 9) Operaciones con rangos de fechas (sin reloj del sistema)

**Objetivo**: Probar una clase `DateRange` (o funciones) con `LocalDate start`, `LocalDate end`.

**Reglas**
- Rango inclusivo: start y end incluidos.
- `start` y `end` no pueden ser `null`.
- `start` no puede ser posterior a `end`.
- Operaciones a testear: `contains(LocalDate date)`, `overlaps(DateRange other)`, `days()` (número de días del rango inclusivo).

**Checklist de tests**
- Construcción válida con misma fecha (rango de 1 día).
- Rechaza `start` null.
- Rechaza `end` null.
- Rechaza `start` > `end`.
- `contains` devuelve true para `start` y para `end`.
- `contains` devuelve false para el día anterior a `start` y posterior a `end`.
- `overlaps` true cuando comparten al menos un día.
- `overlaps` true cuando uno contiene completamente al otro.
- `overlaps` false cuando son disjuntos (end del primero antes del start del segundo).
- `days()` correcto en rangos cortos (p. ej. 2026-02-05 a 2026-02-07 ⇒ 3).

---

## 10) Estadísticas de una lista de enteros

**Objetivo**: Probar una clase/función `stats(List<Integer> values)` que devuelve un resultado con:
- mínimo, máximo, media (double), mediana (double).

**Reglas**
- No acepta lista `null`.
- No acepta lista vacía.
- Define si permite elementos `null` dentro de la lista (recomendado: no) y testea.
- Mediana:
  - Si hay tamaño impar: elemento central tras ordenar.
  - Si hay tamaño par: promedio de los dos centrales.

**Checklist de tests**
- Lista impar: [1, 3, 2] ⇒ min=1, max=3, mediana=2.
- Lista par: [1, 2, 3, 4] ⇒ mediana=2.5.
- Lista con negativos: [-5, 0, 5].
- Lista con repetidos: [2,2,2] (min/max/mediana/media coherentes).
- Rechaza lista vacía.
- Rechaza lista `null`.
- Si no permites `null` internos: [1, null, 2] debe fallar.
- Prueba de posibles desbordamientos: valores grandes (p. ej. `Integer.MAX_VALUE`) y comprueba que la media se calcula sin overflow.
