#define bool _Bool

unsigned char strlt(char *s, char *t) { 
  return strcmp(s, t) < 0; 
}

unsigned char strle(char *s, char *t) { 
  return strcmp(s, t) <= 0; }

unsigned char strgt(char *s, char *t) { 
  return strcmp(s, t) > 0;
}

unsigned char strge(char *s, char *t) { 
  return strcmp(s, t) >= 0; }

unsigned char streq(char *s, char *t) { 
  return strcmp(s, t) == 0; 
}

unsigned char strneq(char *s, char *t) { 
  return strcmp(s, t) != 0; 
}

char *getString() {
  char *s = malloc(1 << 8);
  scanf("%s", s);
  return s;
}

int getInt() {
  int x;
  scanf("%d", &x);
  return x;
}

char *toString(int x) {
  char *s = malloc(1 << 4);
  sprintf(s, "%d", x);
  return s;
}

char *substring(char *s, int l, int r) {
  char *t = malloc(r - l + 1);
  for (int i = l; i < r; i++) {
    t[i - l] = s[i];
  }
  t[r - l] = '\0';
  return t;
}

int parseInt(char *s) {
  int x;
  sscanf(s, "%d", &x);
  return x;
}

int ord(char *s, int x) { 
  return s[x]; 
}

char *stradd(char *s, char *t) {
  char *p = malloc(strlen(s) + strlen(t) + 1);
  strcpy(p, s);
  strcat(p, t);
  return p;
}

void print(char *s) { 
  printf("%s", s); 
}

void println(char *s) { 
  printf("%s\n", s); 
}

void printInt(int x) { 
  printf("%d", x); 
}

void printlnInt(int x) { 
  printf("%d\n", x); 
}

int array_size(void *__this) {
    return ((int*)__this)[-1];
}

void *newPtrArray(int size) {

    int *array = malloc((size << 2) + 4);
    array[0] = size;
    return array + 1;
}

void *newClass(int size) {

    int *cl = malloc(size );
    // array[0] = size;
    return cl;
}

// void *__newIntArray(int size) {
//     int *array = malloc((size << 2) + 4);
//     array[0] = size;
//     return array + 1;
// }

// void *__newBoolArray(int size) {
//     int *array = malloc(size + 4);
//     array[0] = size;
//     return array + 1;
// }


