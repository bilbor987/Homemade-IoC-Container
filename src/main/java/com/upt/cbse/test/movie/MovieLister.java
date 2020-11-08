package com.upt.cbse.test.movie;

public class MovieLister {

    private MovieFinder finder;

    public MovieFinder getFinder() {
        return finder;
    }

    public void setFinder(MovieFinder finder) {
        this.finder = finder;
    }

    public String toString() {
        return "MovieLister{\n\tMovieFinder: " + finder + "\n}";
    }
}
