package pe.edu.upeu.asistenciaupeujcr.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.edu.upeu.asistenciaupeujcr.repository.ActividadRepository
import pe.edu.upeu.asistenciaupeujcr.repository.ActividadRepositoryImp
import pe.edu.upeu.asistenciaupeujcr.repository.ComentarioRepository
import pe.edu.upeu.asistenciaupeujcr.repository.EventoAmbientalRepository
import pe.edu.upeu.asistenciaupeujcr.repository.EventoAmbientalRepositoryImp
import pe.edu.upeu.asistenciaupeujcr.repository.MaterialesxRepository
import pe.edu.upeu.asistenciaupeujcr.repository.MaterialesxRepositoryImp
import pe.edu.upeu.asistenciaupeujcr.repository.NoticiaAmbientalRepository
import pe.edu.upeu.asistenciaupeujcr.repository.NoticiaAmbientalRepositoryImp
import pe.edu.upeu.asistenciaupeujcr.repository.UsuarioRepository
import pe.edu.upeu.asistenciaupeujcr.repository.UsuarioRepositoryImp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun userRepository(userRepos:UsuarioRepositoryImp):UsuarioRepository

    @Binds
    @Singleton
    abstract fun actividadRepository(actRepos:ActividadRepositoryImp):ActividadRepository
    @Binds
    @Singleton
    abstract fun materialesxRepository(actRepos: MaterialesxRepositoryImp): MaterialesxRepository
    @Binds
    @Singleton
    abstract fun eventoambientalRepository(facRepos:EventoAmbientalRepositoryImp):EventoAmbientalRepository
    @Binds
    @Singleton
    abstract fun noticiaAmbientalRepository(repository: NoticiaAmbientalRepositoryImp): NoticiaAmbientalRepository

    @Binds
    @Singleton
    abstract fun ComentarioRepository(facRepos:ComentarioRepositoryImp):ComentarioRepository

}

class ComentarioRepositoryImp {

}
