# Introduction

## Threads are everywhere

Frameworks introduce concurrency into applications by calling application components from framework threads.
Components invariably access application state, thus requiring that *all* code paths accessing that state be 
thread-safe.