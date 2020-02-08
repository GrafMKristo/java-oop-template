package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors = new Author[]{};

    /**
     * Метод должен сохранять автора в массив authors.
     * Массив при каждом сохранении должен увеличиваться в размере ровно на 1.
     * То есть он ровно того размера, сколько сущностей мы в него сохранили.
     * <p>
     * Если на вход для сохранения приходит автор, который уже есть в массиве (сохранен), то автор не сохраняется и
     * метод возвращает false.
     * <p>
     * Можно сравнивать только по полному имени (имя и фамилия), считаем, что двух авторов
     * с одинаковыми именем и фамилией быть не может.
     * Подсказка - можно использовать для проверки метод findByFullName.
     * <p>
     * Если сохранение прошло успешно, метод должен вернуть true.
     *
     * @param author
     */
    @Override
    public boolean save(Author author) {
        int authorsLength = count();
        if (findByFullName(author.getName(), author.getLastName()) != null) {
            return false;
        } else {
            Author[] newAuthorsArray = new Author[authorsLength + 1];
            System.arraycopy(authors, 0, newAuthorsArray, 0, authorsLength);
            newAuthorsArray[authorsLength + 1] = author;
            authors = newAuthorsArray;
            return true;
        }

    }

    /**
     * Метод должен находить в массиве authors автора по имени и фамилии (считаем, что двух авторов
     * с одинаковыми именем и фамилией быть не может.)
     * <p>
     * Если автор с таким именем и фамилией найден - возвращаем его, если же не найден, метод должен вернуть null.
     *
     * @param name
     * @param lastname
     */
    @Override
    public Author findByFullName(String name, String lastname) {
        int authorsLength = count();
        for (int i = 0; i < authorsLength; i++) {
            if (authors[i].getName() == name && authors[i].getLastName() == lastname) return authors[i];
        }
        return null;
    }

    /**
     * Метод должен удалять автора из массива authors, если он там имеется.
     * Автора опять же, можно определять только по совпадению имении и фамилии.
     * <p>
     * Важно: при удалении автора из массива размер массива должен уменьшиться!
     * То есть, если мы сохранили 2 авторов и вызвали count() (метод ниже), то он должен вернуть 2.
     * Если после этого мы удалили 1 автора, метод count() должен вернуть 1.
     * <p>
     * Если автор был найден и удален, метод должен вернуть true, в противном случае, если автор не был найден, метод
     * должен вернуть false.
     *
     * @param author
     */
    @Override
    public boolean remove(Author author) {
        int positionToDelete = -1;
        int authorsLength = count();
        for (int i = 0; i < authorsLength; i++) {
            if (authors[i] == author) {
                positionToDelete = i;
                break;
            }
        }
        if (positionToDelete != -1) {
            Author[] newAuthorsArray = new Author[authorsLength - 1];
            System.arraycopy(authors, 0, newAuthorsArray, 0, positionToDelete);
            System.arraycopy(authors, positionToDelete + 1, newAuthorsArray, positionToDelete, authorsLength - positionToDelete - 1);
/*            int newArrayCounter = 0;
            for (int i = 0; i < authorsLength; i++) {
                if (i == positionToDelete) continue;
                newAuthorsArray[newArrayCounter++] = authors[i];
            }*/
            authors = newAuthorsArray;
            return true;
        } else return false;
    }

    /**
     * Метод возвращает количество сохраненных сущностей в массиве authors.
     */
    @Override
    public int count() {
        return authors.length;
    }
}
