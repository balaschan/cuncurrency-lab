package com.grid.cuncurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    public void acquireReadLock() {
        readLock.lock();
        try {
            doRead();
        } finally {
            readLock.unlock();
        }
    }

    public void acquireWriteLock() {
        writeLock.lock();
        try {
            doWrite();
        } finally {
            writeLock.unlock();
        }
    }

    public void downgradeWriteLock() {
        writeLock.lock();
        try {
            doWrite();
            readLock.lock();
        } finally {
            writeLock.unlock();
        }

        try {
            doRead();
        } finally {
            readLock.unlock();
        }
    }

    private void doRead() {
        System.out.println("Reading.");
    }

    private void doWrite() {
        System.out.println("Writing.");
    }

    public static void main(String[] args) {
        final ReadWriteLockExample readWriteLockDetails = new ReadWriteLockExample();
        readWriteLockDetails.acquireReadLock();
        readWriteLockDetails.acquireWriteLock();
    }

}
