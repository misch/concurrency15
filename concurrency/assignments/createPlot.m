load 'measurements.mat';

threads = [1 2 4 8];
noSync = (noSync1 + noSync2 + noSync3) ./ 3;
sync = (sync1 + sync2 + sync3) ./ 3;
reenteredLock = (reenteredLock1 + reenteredLock2 + reenteredLock3) ./ 3;

figure(1);
plot(threads, noSync, threads, sync, threads, reenteredLock);
xlabel('#threads');
ylabel('execution time [ms]');
legend('not synchronized','synchronized','reentered lock');