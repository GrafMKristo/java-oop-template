package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {

    private SchoolBook[] schoolBooks = new SchoolBook[]{};

    /**
     * Метод должен сохранять школьную книгу в массив schoolBooks.
     * Массив при каждом сохранении должен увеличиваться в размере ровно на 1.
     * То есть он ровно того размера, сколько сущностей мы в него сохранили.
     * <p>
     * Одну и ту же книгу МОЖНО сохранить в массиве несколько раз, проверки на то, что такая книга уже сохранена, делать не нужно.
     * <p>
     * Если сохранение прошло успешно, метод должен вернуть true.
     *
     * @param book
     */
    @Override
    public boolean save(SchoolBook book) {
        int booksLength = count();
        SchoolBook[] newSchoolBooks = new SchoolBook[booksLength + 1];
        System.arraycopy(schoolBooks, 0, newSchoolBooks, 0, booksLength);
        newSchoolBooks[booksLength] = book;
        schoolBooks = newSchoolBooks;

        if (count() - booksLength == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Метод должен находить в массиве schoolBooks все книги по имени.
     * <p>
     * Если книги найдены - метод должен их вернуть.
     * Если найденных по имени книг нет, должен вернуться пустой массив.
     *
     * @param name
     */
    @Override
    public SchoolBook[] findByName(String name) {
        int booksLength = count();
        SchoolBook[] resultArray = new SchoolBook[booksLength];
        int foundCounter = 0;
        for (int i = 0; i < booksLength; i++) {
            if (schoolBooks[i].getName() == name) {
                resultArray[foundCounter++] = schoolBooks[i];
            }
        }
        return Arrays.copyOf(resultArray, foundCounter);
    }

    /**
     * Метод должен удалять книги из массива schoolBooks по названию.
     * Если книг с одинаковым названием в массиве несколько, метод должен удалить их все.
     * <p>
     * Важно: при удалении книги из массива размер массива должен уменьшиться!
     * То есть, если мы сохранили 2 разные книги и вызвали count() (метод ниже), то он должен вернуть 2.
     * Если после этого мы удалили 1 книгу, метод count() должен вернуть 1.
     * <p>
     * Если хотя бы одна книга была найдена и удалена, метод должен вернуть true, в противном случае,
     * если книга не была найдена, метод должен вернуть false.
     *
     * @param name
     */
    @Override
    public boolean removeByName(String name) {
        int booksLength = count();
        SchoolBook[] newSchoolBooks = new SchoolBook[booksLength];
        int counter = 0;
        for (int i = 0; i < booksLength; i++) {
            String bookName = schoolBooks[i].getName();
            boolean areNamesEqual = bookName == name;
            if (!areNamesEqual) {
                newSchoolBooks[counter++] = schoolBooks[i];
            }
        }
        if (counter != booksLength) {
            schoolBooks = Arrays.copyOf(newSchoolBooks, counter);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Метод возвращает количество сохраненных сущностей в массиве schoolBooks.
     */
    @Override
    public int count() {
        return schoolBooks.length;
    }
}
