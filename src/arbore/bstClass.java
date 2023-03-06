/**
 * clasa bstClass este o clasa construita pentru a stoca un arbore binar de cautare care ajuta aplicatia prin viteza lui de cautare a minimului si maximului dintr-un arbore
 */

package arbore;

import Produse.*;
public class bstClass {
    /**
     * clasa Node stocheaza in ea drept cheie un obiect de tip Produs cat si doua alte noduri reprezentand nodul din stanga si din dreapta
     */
    public static class Node {
        public Produs a;
        Node left, right;
        public Node(Produs data){
            a = data;
            left = right = null;
        }
    }
    public Node root;

    public bstClass(){
        root = null;
    }

    /**
     *
     * @param root = radacina arborelui
     * @param key = cheia nodului pe care dorim sa-l stergem
     * @return metoda returneaza noua radacina rezultata din reordonarea arborelui dupa stergerea nodului cu cheia key
     */
    public Node stergere(Node root, double key)  {
        if (root == null)  return root;

        if (key < root.a.getNrComenzi())
            root.left = stergere(root.left, key);
        else if (key > root.a.getNrComenzi())
            root.right = stergere(root.right, key);
        else  {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.a.setNrComenzi(minValue(root.right));

            root.right = stergere(root.right, root.a.getNrComenzi());
        }
        return root;
    }

    /**
     * @param root = radacina arborelui
     * @return metoda returneaza cea mai mica valoarea din arbore
     * returneaza tip int
     */
    public int minValue(Node root)  {
        int minVal = root.a.getNrComenzi();
        while (root.left != null)  {
            minVal = root.left.a.getNrComenzi();
            root = root.left;
        }
        return minVal;
    }

    /**
     * @param root = radacina arborelui
     * @return metoda returneaza cea mai mica valoarea din arbore
     * returneaza tip Node
     */
    public Node minValueNode(Node root)  {
        int minVal = root.a.getNrComenzi();
        while (root.left != null)  {
            minVal = root.left.a.getNrComenzi();
            root = root.left;
        }
        return root;
    }

    /**
     * @param root = radacina arborelui
     * @return metoda returneaza cea mai mare valoarea din arbore
     * returneaza tip int
     */
    public int maxValue(Node root)  {
        int maxVal = root.a.getNrComenzi();
        while (root.right != null)  {
            maxVal = root.right.a.getNrComenzi();
            root = root.right;
        }
        return maxVal;
    }

    /**
     * @param root = radacina arborelui
     * @return metoda returneaza cea mai mare valoarea din arbore
     * returneaza tip Node
     */
    public Node maxValueNode(Node root)  {
        int maxVal = root.a.getNrComenzi();
        while (root.right != null)  {
            maxVal = root.right.a.getNrComenzi();
            root = root.right;
        }
        return root;
    }

    /**
     * @param key = nodul nou care trebuie adaugat
     * metoda se bazeaza pe functia de inserare recursiva pentru a adauga un nod nou
     */
    public void insert(Produs key)  {
        root = insertRecursiv(root, key);
    }

    /**
     *
     * @param root = radacina arborelui
     * @param key = nodul nou care trebuie adaugat
     * @return dupa adaugarea nodului nou se returneaza punctul de plecare adica radacina arborelui
     * metoda se bazeaza pe principiul de baza al bst adica elementele adaugate noi care sunt mai mici decat radacina se adauga in stanga
     * iar cele care sunt mai mari se adauga in dreapta
     * conditia de oprire a metodei este cazul in care, dupa parcurgerea in functie de cheile nodurilor adica in stanga sau in dreapta,
     * se ajunge la o frunza null, atunci se adauga un nod nou cu cheia primita prin parametrul key
     */
    public Node insertRecursiv(Node root, Produs key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }
        if (key.getNrComenzi() < root.a.getNrComenzi())
            root.left = insertRecursiv(root.left, key);
        else if (key.getNrComenzi() > root.a.getNrComenzi())
            root.right = insertRecursiv(root.right, key);
        return root;
    }

    /**
     *
     * @param root
     * @param key
     * @return
     * cautarea se face in functie de valoarea la care se ajunge fie in stanga fie in dreapta arborelui
     * in momentul in care se gaseste elementul se returneaza nodul respectiv
     */
    public Node cautareRecursiva(Node root, double key)  {
        if (root==null || root.a.getNrComenzi()==key)
            return root;
        if (root.a.getNrComenzi() > key)
            return cautareRecursiva(root.left, key);

        return cautareRecursiva(root.right, key);
    }

}
