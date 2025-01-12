# WhoWasIt

-ejecutar el intelli j : pulsar dos vecs la tecla control y la opcion mvn spring-boot:run (algo asi) <br>
-ejecutar el visual studio, con la git bash vas a la carpeta del proyecto WhoWasIt (haciendo cd documents/ y eso ), una vez dentro de esac carpeta te metes en la carpeta who-was-it 
  una vez metido haces code . (para brir visual studio y haces este comando ng serve en la misma terminal del git bash)<br>
  <h1>Te ves capacitado para hacer el apartado de estadisticas de una publicacion??</h1> <br>
  En el intelli j esta hecho el codigo y funciona, solo hace falta mostrarlo en angular (en el code) <br>
  Las estadisticas es que un usuario pueda ver quien le ha dado mg a la publicacion, comentado y si es el caso de un formulario quien ha votado. <br>
  <h3>Te explico un poco mas ( por si eres capaz de hacerlo, No pasa nada si al intentarlo haces que deje de funcionar alguna cosa o falla algo recuerda que estas en tu rama y ami no me afecta)</h3>
  <h4>Pasos para como saber hacerlo</h4>
  <h4>Paso 1:</h4> Te tienes que ir al intelli j y te tienes que meter en la carpeta postear , una vez metido , te vas a la carpeta Controller y abres la clase PostearController. <br>
  Tienes que buscar esto : @GetMapping("usuario/estadisticas/del/post/{id}") que creo que esta en la linea 51 (si no recuerdo mal), esa es la url a la que tienes que llamar desde el code.(mas alante te explico..)
  <h4>Paso 2:</h4> Si no sabes para que sirve que es el {id} de esa url , es para saber que publicacion quieres mirar sus estadisticas , Fin. Luego te vas al code , una vez metido , tienes que entrar en la carpeta src y luego a la carpeta app , 
  una vez dentro de la carpeta app veras carpetas como : componentes,pages,models y service (no se si me dejo alguna mas). Te tienes que ir a la carpeta models y buscar el archivo que se llama estadisticas-del-post.ts (creo que se llama asi)
  una vez dentro copias de la linea 1 la palabra que viene despues de interfaze que es EstadisticasDelPost (Lo copias: control + c no me seas cateto).
  <h4>Paso 3:</h4> Una vez copiado te tienes que ir a la carpeta service y dentro de la carpeta service abrir el archivo de esa carpeta (solo hay uno, no creo que te pierdas), una vez abierto veras muchas lineas de codigo sheee no te asustes , escrolleas hasta       abajo del todo, una vez abajo del todo. Mira si existe en la linea 343 un metodo que se llama estadisticasDelPost, si no existe copia esto y lo pegas :   estadisticasDelPost(id:string):Observable<EstadisticasDelPost>{
    let token = localStorage.getItem('TOKEN');

    return this.http.get<EstadisticasDelPost>(`${this.url}/usuario/estadisticas/del/post/${id}`,
     {
        headers: {
          accept: 'application/json',
          'Authorization': `Bearer ${token}`
        }
      });

  }
<br>
<h4>Paso 4 :</h4> Una vez hecho esto, te vas a la carpeta home-page y te encontraras 3 archivos terminados por .css,.html,.ts . Abres el que termina por (.ts) una vez abierto tienes que llamar al metodo del servicio (tranquilo yo te lo explico como ) <br>
En cualquier hueco libres que vea del archivo por ejemplo en la linea 193 vas a crear el metodo que para llamar al servicio, ponle un nombre por ejemplo estadisticasDeUnPOst (mismamente , ponle un nombre que te aclare), una vez puesto el nombre <br>
tienes que poner esto : () y dentro de los parentesis esto : (idPost: string) y fuera del parentesis abre llaves . Quedaria una cosa tal que asi : [nombreDelMetodo](idPost: string){} (cuando hallas puesto las llaves dentro de ella pulsa una cuantas <br>
la tecla intro), dentro de las llaves tienes que poner lo siguiente, this.service.[el nombre del meteodo del servicio para las estadisticas ( si no te acuerdas como se llama vete a la carpeta service y dentro del archivo mira el nombre del metodo , <br>
el metodo que te he dicho en el paso 3 que copiaras), una vez puesto abre parentesis y pon dentro de los parentesis idPost, una vez puesto fuera de los parentesis pon un punto y pon subscribe y vuelves abrir parantesis y dentro de los parentesis pon esto:<br>
r=>{} , (dentro de las llaves dale un par de veces a la tecla intro). y dentro de las llaves pon this.estadisticas = r;. OJO , TE DARA UN ERROR, no pasa nada te dira que en ese archivo no hay ningun nombre de variable que se llame estadisticas. Lo que tienes <br>
que hacer es irte a la liena 44 y poner esto estadististicas!:EstadisticasDelPost; y veras como no te salta ese error. Vale Pues lo dificil ya esta terminado , ahora solo falta mostrarlo para que el usuario lo vea.

<h4>Paso 5:</h4> en la carpeta de home-page metete en el archivo que termina en .html, y como veras en el navegador en el rectangulo de la pueblicacion veras un boton de estadisticas, lo suyo seria que cuando pulsaras ese boton apareciera un modal <br>
(un modal es lo que sale cuando pulsas el boton que esta a la derecha del buscar , es decir el de para crear una publicacion que tiene un icono de una carta con un +), eso seria lo suyo ( si tienes otra idea que piensa que puede quedar mejor<br>
adelante ), vale te vas a la linea 58 te encontras con esto: type="button" pues desppues de las comillas pulsa la tecla intro , y lo que tienes que hacer es llamar al metodo que has creado en el arhcivo (.ts) (el que acabas de crear ) y debe de quedar asi: <br>
[nombre del metodo](c.id) (el c.id es el id de la publicacion que queremos ver sus estadisticas.Bueno te he dejado con el 95% del trabajo masticadito, ahora te dejo como hacer para que se abra ese modal ;) , esta pagina es la que utlice yo para hacer el modal de crear una publicacion : https://ng-bootstrap.github.io/#/components/modal/examples <br>
te sale un moton de ejemplo de modals elige el que mas te guste , una vez que lo hayas elegido a la derecha aparece dos icono uno que se llama code y otro que se llama stackblitz (o algo asi) pulsas el del code y te muestra el codigo que tienes que copiar y pegar en el html y el codigo que tienes que copiar para el componente. Una vez copiado el codigo para el html y este pegado en el html y lo mismo para el componnete dentro del modal ( que es donde se va a ver la informacion de las estadisticas ) crea un div 
<br> y en el div entre la v y el > tienes que poner esto: *ngFor="let e of estadisticas" (si te da error guardalo todo , te vas a file y pulsa en save all) una vez puesto esto dentro del div , esdecir entre el simbolo del mayor y del menor tienes que poner <br>
{{estadisticas.numeroMegusta}} eso es para mostrar lo que quieras dentro del div , prueba que funcione y con ayuda del chatgpt muestrale el contenido del archivo estadisticas-del-post.ts que esta en la carpeta model y dile que te de ideas de como mostrarlo <br>
para que quede bonito

  
